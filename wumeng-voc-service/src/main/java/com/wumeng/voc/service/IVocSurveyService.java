package com.wumeng.voc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wumeng.common.vo.PageInfo;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.vo.SquareQueryVo;
import com.wumeng.voc.vo.SurveyDetailVo;
import com.wumeng.voc.vo.SurveyQueryReqVo;
import com.wumeng.voc.vo.SurveyQueryVo;

import java.util.List;

/**
 * <p>
 * 问卷信息表 服务类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
public interface IVocSurveyService extends IService<VocSurvey> {

    String create();

//    PageInfo<VocSurvey> findByPage(SurveyQueryReqVo vo);

    void update(String id, VocSurvey survey);

    void batchDelete(List<String> ids);

    void recovery(String id);

    PageInfo<SurveyQueryVo> pageSearch(SurveyQueryReqVo vo);

    SurveyDetailVo getDetail(String id);

    void updateAll(String id, SurveyDetailVo vo);

    String copy(String id);

    PageInfo<VocSurvey> getSquarePage(SquareQueryVo vo);
}
