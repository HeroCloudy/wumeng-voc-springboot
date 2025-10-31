package com.wumeng.voc.security.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 3082876130976250236L;

    private String token;
}
