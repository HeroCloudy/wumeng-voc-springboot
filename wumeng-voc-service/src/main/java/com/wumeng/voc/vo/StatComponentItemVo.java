package com.wumeng.voc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatComponentItemVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 2050685588481311864L;

    private String name;
    private Long count;
}
