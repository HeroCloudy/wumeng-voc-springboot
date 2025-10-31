package com.wumeng.voc.controller;

import com.wumeng.common.vo.PageInfo;
import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.service.IVocSurveyService;
import com.wumeng.voc.vo.SquareQueryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("square")
public class SquareController {

    private final IVocSurveyService vocSurveyService;

    @GetMapping("page")
    public Result<PageInfo<VocSurvey>> page(SquareQueryVo vo){
        return Result.ok(this.vocSurveyService.getSquarePage(vo));
    }
}
