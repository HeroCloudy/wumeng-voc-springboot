package com.wumeng.voc.security.controller;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.sys.SysUser;
import com.wumeng.voc.security.service.AuthService;
import com.wumeng.voc.security.vo.LoginResp;
import com.wumeng.voc.security.vo.LoginVo;
import com.wumeng.voc.security.vo.RegisterVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public Result<LoginResp> login(@RequestBody LoginVo vo) {
        return this.authService.login(vo);
    }

    @GetMapping("logout")
    public void logout() {
        this.authService.logout();
    }

    @GetMapping("profile")
    public Result<SysUser> getProfile() {
        return Result.ok(this.authService.getUserInfo());
    }

    @PostMapping("register")
    public Result<Void> register(@RequestBody RegisterVo vo) {
        this.authService.register(vo);
        return Result.ok();
    }
}
