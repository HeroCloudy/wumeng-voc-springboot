package com.wumeng.voc.vo;

import com.wumeng.common.vo.PageReq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyQueryReqVo extends PageReq {

    private String keyword;
    private Boolean isPublished;
    private Boolean isDeleted;
    private Boolean isStar;
    private String createBy;
}
