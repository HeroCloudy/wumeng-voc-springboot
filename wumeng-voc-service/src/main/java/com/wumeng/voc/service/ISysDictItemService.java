package com.wumeng.voc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wumeng.voc.entity.sys.SysDictItem;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典项表 服务类
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-30 09:36:16
 */
public interface ISysDictItemService extends IService<SysDictItem> {

    List<SysDictItem> getDictItemList(String dictCode);

    Map<String, List<SysDictItem>> getDictItemListMap(List<String> dictCodeList);
}
