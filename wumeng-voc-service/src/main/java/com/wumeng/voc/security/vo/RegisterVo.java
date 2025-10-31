package com.wumeng.voc.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVo {

    private String username;
    private String password;
    private String nickname;
}
