package com.wumeng.voc.controller;

import com.wumeng.common.vo.PageInfo;
import com.wumeng.common.vo.PageReq;
import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.service.IVocSurveyService;
import com.wumeng.voc.vo.SurveyBatchDeleteReqVo;
import com.wumeng.voc.vo.SurveyDetailVo;
import com.wumeng.voc.vo.SurveyQueryReqVo;
import com.wumeng.voc.vo.SurveyQueryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * <p>
 * 问卷信息表 前端控制器
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class VocSurveyController {

    private final IVocSurveyService vocSurveyService;

    @PostMapping()
    public Result<String> createSurvey() {
        return Result.ok(this.vocSurveyService.create());
    }

    @GetMapping()
    public Result<PageInfo<SurveyQueryVo>> pageSearch(SurveyQueryReqVo vo) {
        return Result.ok(this.vocSurveyService.pageSearch(vo));
    }

    @PutMapping("{id}")
    public Result<Void> updateSurvey(@PathVariable String id, @RequestBody VocSurvey survey) {
        this.vocSurveyService.update(id, survey);
        return Result.ok();
    }

    @PutMapping("recovery/{id}")
    public Result<Void> recovery(@PathVariable String id) {
        this.vocSurveyService.recovery(id);
        return Result.ok();
    }

    @DeleteMapping("batch")
    public Result<Void> batchDeleteSurvey(@RequestBody SurveyBatchDeleteReqVo vo) {
        this.vocSurveyService.batchDelete(vo.getIds());
        return Result.ok();
    }

    @DeleteMapping("{id}")
    public Result<Void> deleteSurvey(@PathVariable String id) {
        this.vocSurveyService.removeById(id);
        return Result.ok();
    }

    @GetMapping("{id}")
    public Result<SurveyDetailVo> getDetail(@PathVariable String id) {
        return Result.ok(this.vocSurveyService.getDetail(id));
    }

    @PutMapping("update-all/{id}")
    public Result<Void> updateAll(@PathVariable String id, @RequestBody SurveyDetailVo vo) {
        this.vocSurveyService.updateAll(id, vo);
        return Result.ok();
    }

    @PostMapping("copy/{id}")
    public Result<String> copy(@PathVariable String id) {
        return Result.ok(this.vocSurveyService.copy(id));
    }

}
