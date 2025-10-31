package com.wumeng.voc.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wumeng.voc.entity.sys.SysUser;
import com.wumeng.voc.entity.sys.SysUserRole;
import com.wumeng.voc.mapper.SysUserMapper;
import com.wumeng.voc.mapper.SysUserRoleMapper;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser sysUser = this.sysUserMapper.selectOne(wrapper);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 2. 查询权限
        List<SysUserRole> userRoleList = this.sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", sysUser.getId()));
        List<String> roleCodeList = userRoleList.stream().map(SysUserRole::getRoleCode).toList();

        return new SecurityUserDetails(sysUser, roleCodeList);
    }
}
