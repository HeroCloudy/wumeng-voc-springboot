package com.wumeng.voc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wumeng.voc.entity.voc.VocAnswerItem;
import com.wumeng.voc.vo.StatComponentItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问卷回答表 Mapper 接口
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-28 16:58:12
 */
public interface VocAnswerItemMapper extends BaseMapper<VocAnswerItem> {

    List<StatComponentItemVo> getComponentStat(@Param("surveyId") String surveyId, @Param("componentId") String componentId);
}
