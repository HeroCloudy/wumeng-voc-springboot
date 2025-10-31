package com.wumeng.voc.security.service;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.sys.SysUser;
import com.wumeng.voc.security.vo.LoginResp;
import com.wumeng.voc.security.vo.LoginVo;
import com.wumeng.voc.security.vo.RegisterVo;

public interface AuthService {

    Result<LoginResp> login(LoginVo vo);

    void logout();

    void register(RegisterVo registerVo);

    SysUser getUserInfo();
}
