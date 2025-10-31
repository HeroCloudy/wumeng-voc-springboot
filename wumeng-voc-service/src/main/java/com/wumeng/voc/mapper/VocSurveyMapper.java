package com.wumeng.voc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wumeng.common.vo.PageInfo;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.vo.SurveyQueryReqVo;
import com.wumeng.voc.vo.SurveyQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问卷信息表 Mapper 接口
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
public interface VocSurveyMapper extends BaseMapper<VocSurvey> {

    void batchDeletePhysical(@Param("ids") List<String> ids);

    void recovery(String id);

    IPage<SurveyQueryVo>  selectPageVo(IPage<VocSurvey> page, SurveyQueryReqVo vo);
}
