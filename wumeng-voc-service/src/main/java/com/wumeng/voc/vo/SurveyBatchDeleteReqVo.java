package com.wumeng.voc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyBatchDeleteReqVo {

    private List<String> ids = new ArrayList<>();
}
