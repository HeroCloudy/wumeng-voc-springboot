package com.wumeng.voc.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wumeng.voc.entity.voc.VocAnswer;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.mapper.VocAnswerItemMapper;
import com.wumeng.voc.service.IVocAnswerItemService;
import com.wumeng.voc.service.IVocAnswerService;
import com.wumeng.voc.service.IVocStatService;
import com.wumeng.voc.service.IVocSurveyService;
import com.wumeng.voc.vo.StatComponentItemVo;
import com.wumeng.voc.vo.StatRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VocStatServiceImpl implements IVocStatService {

    private final IVocAnswerService vocAnswerService;
    private final VocAnswerItemMapper vocAnswerItemMapper;

    @Override
    public StatRespVo getStatBySurveyId(String surveyId) {
        // 查询问卷总数
        QueryWrapper<VocAnswer> countWrapper = new QueryWrapper<VocAnswer>().eq("survey_id", surveyId);
        Long total = this.vocAnswerService.getBaseMapper().selectCount(countWrapper);

        // 查询答卷列表
        QueryWrapper<VocAnswer> listWrapper = new QueryWrapper<>();
        listWrapper.eq("survey_id", surveyId);
        List<VocAnswer> vocAnswerList = this.vocAnswerService.getBaseMapper().selectList(listWrapper);
        List<JSONObject> list = vocAnswerList.stream().map(VocAnswer::getJsonValue).map(JSONObject::parseObject).toList();

        return new StatRespVo(total, list);
    }

    @Override
    public List<StatComponentItemVo> getStatBySurveyIdAndComponentId(String surveyId, String componentId) {
        return this.vocAnswerItemMapper.getComponentStat(surveyId, componentId);
    }
}
