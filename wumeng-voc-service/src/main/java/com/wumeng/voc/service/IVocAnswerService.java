package com.wumeng.voc.service;

import com.wumeng.voc.entity.voc.VocAnswer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wumeng.voc.vo.SubmitAnswerVo;

/**
 * <p>
 * 问卷回答表 服务类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-28 21:52:22
 */
public interface IVocAnswerService extends IService<VocAnswer> {

    void create(SubmitAnswerVo vo);
}
