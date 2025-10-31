package com.wumeng.voc.controller;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.service.IVocAnswerItemService;
import com.wumeng.voc.service.IVocAnswerService;
import com.wumeng.voc.service.IVocSurveyService;
import com.wumeng.voc.vo.SubmitAnswerVo;
import com.wumeng.voc.vo.SurveyDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("voc")
public class VocController {

    private final IVocSurveyService vocSurveyService;
    private final IVocAnswerService vocAnswerService;

    @GetMapping("{id}")
    public Result<SurveyDetailVo> getDetail(@PathVariable String id) {
        return Result.ok(this.vocSurveyService.getDetail(id));
    }

    @PostMapping("submit")
    public Result<Void> submit(@RequestBody SubmitAnswerVo vo) {
        vocAnswerService.create(vo);
        return Result.ok();
    }
}
