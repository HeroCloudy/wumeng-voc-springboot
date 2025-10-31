package com.wumeng.voc.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wumeng.common.exception.CommonException;
import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.sys.SysUser;
import com.wumeng.voc.entity.sys.SysUserRole;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import com.wumeng.voc.security.utils.JwtUtils;
import com.wumeng.voc.security.vo.LoginResp;
import com.wumeng.voc.security.vo.LoginVo;
import com.wumeng.voc.security.vo.RegisterVo;
import com.wumeng.voc.service.ISysUserRoleService;
import com.wumeng.voc.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ISysUserService sysUserService;
    private final PasswordEncoder passwordEncoder;
    private final ISysUserRoleService sysUserRoleService;

    @Override
    public Result<LoginResp> login(LoginVo vo) {
        String username = vo.getUsername();
        String password = vo.getPassword();

        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);

        try {
            // 发起认证，得到认证结果
            Authentication authentication = this.authenticationManager.authenticate(authRequest);
            if (authentication.isAuthenticated()) {
                // 放入安全上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
                SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
                // 生成 token
                String key = "User:" + userDetails.getUser().getId();
                String token = JwtUtils.createToken(key, 1000 * 60 * 60 * 12L);
                // 写入 redis
                this.redisTemplate.opsForValue().set(key, authentication.getPrincipal(), 1000 * 60 * 60 * 12, TimeUnit.MILLISECONDS);
                // 返回前端JSON
                return Result.ok(new LoginResp(token));
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

        return Result.error(401, "登录失败");
    }

    @Override
    public void logout() {
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String key = "User:" + userDetails.getUser().getId();
        this.redisTemplate.delete(key);
        // 清空安全上下文
        SecurityContextHolder.clearContext();
    }

    @Override
    public void register(RegisterVo registerVo) {
        String username = registerVo.getUsername();

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<SysUser> list = this.sysUserService.getBaseMapper().selectList(wrapper);
        if (!list.isEmpty()) {
            throw new CommonException("账号已存在");
        }
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(registerVo.getPassword()));
        user.setNickname(user.getNickname());
        this.sysUserService.save(user);

        SysUserRole ur = SysUserRole.builder()
                .userId(user.getId())
                .roleCode("VOC_EDITOR")
                .build();
        this.sysUserRoleService.save(ur);
    }

    @Override
    public SysUser getUserInfo() {
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            throw new RuntimeException("登录已失效，请重新登录");
        }
        SysUser user = userDetails.getUser();
        user.setPassword(null);
        return user;
    }
}
