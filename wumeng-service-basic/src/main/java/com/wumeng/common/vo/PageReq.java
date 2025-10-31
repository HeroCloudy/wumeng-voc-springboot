package com.wumeng.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq {

    private int pageNo = 1;

    private int pageSize = 10;

}
