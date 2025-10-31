package com.wumeng.voc.controller;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.entity.sys.SysDictItem;
import com.wumeng.voc.service.ISysDictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统字典表 前端控制器
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-30 09:36:16
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/dict")
public class SysDictController {

    private final ISysDictItemService dictItemService;

    @GetMapping("item-list/{dictCode}")
    public Result<List<SysDictItem>> getDictItemList(@PathVariable String dictCode){
        return Result.ok(this.dictItemService.getDictItemList(dictCode));
    }

    @PostMapping("item-list")
    public Result<Map<String, List<SysDictItem>>> getDictItemListMap(@RequestBody Map<String, List<String>> params) {
        List<String> dictCodes = params.get("dictCodeList");
        if (CollectionUtils.isEmpty(dictCodes)) {
            return Result.ok();
        }
//        if (dictCodes == null || dictCodes.length == 0) {
//            return Result.ok();
//        }
        return Result.ok(this.dictItemService.getDictItemListMap(dictCodes));
    }
}
