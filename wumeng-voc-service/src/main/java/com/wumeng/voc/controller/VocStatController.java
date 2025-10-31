package com.wumeng.voc.controller;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.service.IVocStatService;
import com.wumeng.voc.vo.StatComponentItemVo;
import com.wumeng.voc.vo.StatRespVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("stat")
public class VocStatController {

    private final IVocStatService vocStatService;

    @GetMapping("{surveyId}")
    public Result<StatRespVo> getStatBySurveyId(@PathVariable String surveyId) {
        return Result.ok(vocStatService.getStatBySurveyId(surveyId));
    }

    @GetMapping("{surveyId}/{componentId}")
    public Result<List<StatComponentItemVo>> getStatBySurveyIdAndComponentId(@PathVariable String surveyId, @PathVariable String componentId) {
        return Result.ok(this.vocStatService.getStatBySurveyIdAndComponentId(surveyId, componentId));
    }
}
