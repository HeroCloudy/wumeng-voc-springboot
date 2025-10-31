package com.wumeng.voc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.wumeng.common.exception.CommonException;
import com.wumeng.voc.entity.voc.VocAnswer;
import com.wumeng.voc.entity.voc.VocAnswerItem;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.mapper.VocAnswerItemMapper;
import com.wumeng.voc.mapper.VocAnswerMapper;
import com.wumeng.voc.mapper.VocSurveyMapper;
import com.wumeng.voc.service.IVocAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumeng.voc.vo.SubmitAnswerVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 问卷回答表 服务实现类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-28 21:52:22
 */
@RequiredArgsConstructor
@Service
public class VocAnswerServiceImpl extends ServiceImpl<VocAnswerMapper, VocAnswer> implements IVocAnswerService {

    private final VocSurveyMapper vocSurveyMapper;
    private final VocAnswerItemMapper vocAnswerItemMapper;

    @Override
    public void create(SubmitAnswerVo vo) {
        String surveyId = vo.getSurveyId();

        VocSurvey survey = vocSurveyMapper.selectById(surveyId);
        if (survey == null) {
            throw new CommonException("问卷不存在");
        }
        if (survey.getIsPublished() != 1) {
            throw new CommonException("问卷没有发布");
        }

        Map<String, Object> map = vo.getValues();
        if (CollectionUtils.isEmpty(map)) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        // 保存整体
        VocAnswer vocAnswer = VocAnswer.builder()
                .surveyId(surveyId)
                .submitter(vo.getSubmitter())
                .createTime(now)
                .jsonValue(JSON.toJSONString(map))
                .build();
        this.baseMapper.insert(vocAnswer);

        // 保存明细
        List<VocAnswerItem> list = new ArrayList<>();
        for (Map.Entry<String, Object> item : map.entrySet()) {
            Object obj = item.getValue();
            if (obj instanceof String) {
                list.add(VocAnswerItem.builder()
                        .componentId(item.getKey())
                        .answerId(vocAnswer.getId())
                        .value((String) obj)
                        .surveyId(surveyId)
                        .submitter(vo.getSubmitter())
                        .createTime(now)
                        .build());
            } else if (obj instanceof List) {
                List<String> vs = (List<String>) obj;
                for (String v : vs) {
                    list.add(VocAnswerItem.builder()
                            .componentId(item.getKey())
                            .value(v)
                            .surveyId(surveyId)
                            .submitter(vo.getSubmitter())
                            .createTime(now)
                            .build());
                }
            }
        }

        this.vocAnswerItemMapper.insert(list);
    }
}
