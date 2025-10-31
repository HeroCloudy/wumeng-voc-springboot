package com.wumeng.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSortVo {

    private String sortField = "create_time";

    private String sortType = "desc";
}
