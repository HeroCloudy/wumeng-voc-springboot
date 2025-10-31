package com.wumeng.voc.security.service;

import com.wumeng.voc.entity.sys.SysUser;
import com.wumeng.voc.mapper.SysUserMapper;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsPasswordServiceImpl implements UserDetailsPasswordService {

    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        SecurityUserDetails userDetails = (SecurityUserDetails) user;
        SysUser newUser = userDetails.getUser();
        System.out.println("更新密码: " + newPassword);
        if (newUser != null) {
            newUser.setPassword(newPassword);
            this.sysUserMapper.updateById(newUser);
        }
        return user;
    }
}
