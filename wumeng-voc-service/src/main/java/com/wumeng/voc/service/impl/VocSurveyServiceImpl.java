package com.wumeng.voc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumeng.common.exception.CommonException;
import com.wumeng.common.vo.PageInfo;
import com.wumeng.voc.entity.voc.VocComponent;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.mapper.VocComponentMapper;
import com.wumeng.voc.mapper.VocSurveyMapper;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import com.wumeng.voc.service.IVocSurveyService;
import com.wumeng.voc.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 问卷信息表 服务实现类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
@RequiredArgsConstructor
@Service
public class VocSurveyServiceImpl extends ServiceImpl<VocSurveyMapper, VocSurvey> implements IVocSurveyService {

    private final VocComponentMapper vocComponentMapper;

    @Override
    public String create() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        VocSurvey survey = VocSurvey.builder()
                .title("问卷标题" + uuid)
                .description("问卷描述信息")
                .build();
        this.baseMapper.insert(survey);
        return survey.getId();
    }

//    @Override
//    public PageInfo<VocSurvey> findByPage(SurveyQueryReqVo vo) {
//        IPage<VocSurvey> page = new Page<>(vo.getPageNo(), vo.getPageSize());
//        QueryWrapper<VocSurvey> wrapper = new QueryWrapper<>();
//        if (StringUtils.hasText(vo.getKeyword())) {
//            wrapper.like("title", vo.getKeyword()).or().like("description", vo.getKeyword());
//        }
//        if (vo.getIsDeleted() != null) {
//            wrapper.eq("is_deleted", vo.getIsDeleted());
//        }
//        if (vo.getIsPublished() != null) {
//            wrapper.eq("is_published", vo.getIsPublished());
//        }
//        if (vo.getIsStar() != null) {
//            wrapper.eq("is_star", vo.getIsStar());
//        }
//        if (StringUtils.hasText(getCurrentUserId())) {
//            wrapper.eq("create_by", getCurrentUserId());
//        }
//
//        IPage<VocSurvey> result = this.baseMapper.selectPage(page, wrapper);
//        return new PageInfo<>(result.getTotal(), result.getRecords());
//    }

    @Override
    public void update(String id, VocSurvey survey) {
        survey.setId(id);
        this.baseMapper.updateById(survey);
    }

    @Override
    public void batchDelete(List<String> ids) {
        this.baseMapper.batchDeletePhysical(ids);
    }

    @Override
    public void recovery(String id) {
        this.baseMapper.recovery(id);
    }

    @Override
    public PageInfo<SurveyQueryVo> pageSearch(SurveyQueryReqVo vo) {
        IPage<VocSurvey> page = new Page<>(vo.getPageNo(), vo.getPageSize());
        vo.setCreateBy(getCurrentUserId());
        IPage<SurveyQueryVo> result = this.baseMapper.selectPageVo(page, vo);
        return new PageInfo<>(result.getTotal(), result.getRecords());
    }

    @Override
    public SurveyDetailVo getDetail(String id) {
        VocSurvey survey = this.baseMapper.selectById(id);
        if (survey == null) {
            throw new CommonException("未查询到问卷信息");
        }

        QueryWrapper<VocComponent> wrapper = new QueryWrapper<>();
        wrapper.eq("survey_id", id);
        wrapper.orderBy(true, true, "sort_num");
        List<VocComponent> components = this.vocComponentMapper.selectList(wrapper);

        SurveyDetailVo vo = new SurveyDetailVo();
        BeanUtils.copyProperties(survey, vo);
        vo.setComponentList(components.stream()
                .map(ComponentVo::buildVo)
                .toList());
        return vo;
    }

    @Override
    public void updateAll(String id, SurveyDetailVo vo) {
        VocSurvey survey = new VocSurvey();
        BeanUtils.copyProperties(vo, survey);
        survey.setId(id);
        this.baseMapper.updateById(survey);

        QueryWrapper<VocComponent> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("survey_id", id);
        this.vocComponentMapper.delete(deleteWrapper);

        List<ComponentVo> voList = vo.getComponentList();
        List<VocComponent> list = new ArrayList<>();
        for (int i = 0; i < voList.size(); i++) {
            VocComponent entity = ComponentVo.buildEntity(voList.get(i));
            entity.setSurveyId(id);
            entity.setSortNum(i);
            list.add(entity);
        }
        this.vocComponentMapper.insert(list);
    }

    @Override
    public String copy(String id) {
        SurveyDetailVo vo = getDetail(id);

        VocSurvey survey = new VocSurvey();
        BeanUtils.copyProperties(vo, survey);
        survey.setId(null);
        survey.setTitle(vo.getTitle() + "副本");
        survey.setIsPublished((byte) 0);
        survey.setIsShare((byte) 0);
        survey.setIsStar((byte) 0);
        this.baseMapper.insert(survey);

        List<VocComponent> list = vo.getComponentList().stream().map(item -> {
            VocComponent entity = ComponentVo.buildEntity(item);
            entity.setId(null);
            entity.setSurveyId(survey.getId());
            return entity;
        }).toList();
        this.vocComponentMapper.insert(list);

        return survey.getId();
    }

    @Override
    public PageInfo<VocSurvey> getSquarePage(SquareQueryVo vo) {
        IPage<VocSurvey> page = new Page<>(vo.getPageNo(), vo.getPageSize());
        QueryWrapper<VocSurvey> wrapper = new QueryWrapper<>();
        wrapper.eq("is_share", 1);
        if (StringUtils.hasText(vo.getCategory())) {
            wrapper.eq("category", vo.getCategory());
        }
        if (StringUtils.hasText(vo.getLabel())) {
            wrapper.like("label", vo.getLabel());
        }
        if (StringUtils.hasText(vo.getKeyword())) {
            wrapper.like("title", vo.getKeyword()).or().like("description", vo.getKeyword());
        }
        IPage<VocSurvey> result = this.baseMapper.selectPage(page, wrapper);
        return new PageInfo<>(result.getTotal(), result.getRecords());
    }

    private String getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof SecurityUserDetails) {
            return ((SecurityUserDetails) principal).getUser().getId();
        }
        return null;
    }
}
