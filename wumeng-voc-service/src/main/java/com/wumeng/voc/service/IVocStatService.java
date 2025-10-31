package com.wumeng.voc.service;

import com.wumeng.voc.vo.StatComponentItemVo;
import com.wumeng.voc.vo.StatRespVo;

import java.util.List;

public interface IVocStatService {

    StatRespVo getStatBySurveyId(String surveyId);

    List<StatComponentItemVo> getStatBySurveyIdAndComponentId(String surveyId, String componentId);
}
