package com.wumeng.voc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumeng.voc.entity.sys.SysDictItem;
import com.wumeng.voc.mapper.SysDictItemMapper;
import com.wumeng.voc.service.ISysDictItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典项表 服务实现类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-30 09:36:16
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

    @Transactional
    @Override
    public List<SysDictItem> getDictItemList(String dictCode) {
        QueryWrapper<SysDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_code", dictCode);
        queryWrapper.orderByAsc("sort_num");
        return this.list(queryWrapper);
    }

    @Override
    public Map<String, List<SysDictItem>> getDictItemListMap(List<String> dictCodeList) {
        Map<String, List<SysDictItem>> map = new HashMap<>();
        for (String dictCode : dictCodeList) {
            map.put(dictCode, this.getDictItemList(dictCode));
        }
        return map;
    }
}
