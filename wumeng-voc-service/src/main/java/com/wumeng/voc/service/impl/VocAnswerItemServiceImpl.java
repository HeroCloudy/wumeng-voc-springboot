package com.wumeng.voc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumeng.voc.entity.voc.VocAnswerItem;
import com.wumeng.voc.mapper.VocAnswerItemMapper;
import com.wumeng.voc.service.IVocAnswerItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问卷回答表 服务实现类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-28 16:58:12
 */
@RequiredArgsConstructor
@Service
public class VocAnswerItemServiceImpl extends ServiceImpl<VocAnswerItemMapper, VocAnswerItem> implements IVocAnswerItemService {

}
