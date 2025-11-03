package com.wumeng.voc.ai.tools;

import com.alibaba.fastjson2.JSON;
import com.wumeng.voc.ai.tools.vo.CreateComponentItem;
import com.wumeng.voc.ai.tools.vo.CreateSurveyReq;
import com.wumeng.voc.entity.sys.SysDictItem;
import com.wumeng.voc.entity.voc.VocComponent;
import com.wumeng.voc.entity.voc.VocSurvey;
import com.wumeng.voc.service.ISysDictItemService;
import com.wumeng.voc.service.IVocComponentService;
import com.wumeng.voc.service.IVocSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class VocTools {

    private final IVocSurveyService vocSurveyService;
    private final IVocComponentService vocComponentService;
    private final ISysDictItemService dictItemService;

    private List<String> getDictValueList(String dictCode) {
        List<SysDictItem> list = this.dictItemService.getDictItemList(dictCode);
        return list.stream().map(SysDictItem::getDictValue).toList();
    }

    @Tool(description = "获取全部问卷类型")
    public List<String> getAllCategoryList() {
        System.out.println("----- 调用工具：【获取全部问卷类型】");
        return this.getDictValueList("voc_category");
    }

    @Tool(description = "获取全部标签")
    public List<String> getHotLableList() {
        System.out.println("----- 调用工具：【获取全部标签】");
        return this.getDictValueList("voc_hot_label");
    }

    @Tool(description = "创建问卷")
    @Transactional
    public String createVoc(CreateSurveyReq input) {
        try {
            VocSurvey entity = new VocSurvey();
            BeanUtils.copyProperties(input, entity);
            entity.setId(null);
            entity.setJs("");
            entity.setCss("");
            entity.setIsPublished((byte) 0);
            entity.setIsStar((byte) 0);
            entity.setIsShare((byte) 0);

            System.out.println("保存前的问卷实体：" + JSON.toJSONString(entity));

            // 检查vocSurveyService是否为null
            if (this.vocSurveyService == null) {
                System.err.println("vocSurveyService未注入！");
            }

            this.vocSurveyService.save(entity);
            System.out.println("----- 调用工具：【创建问卷】。" + entity);
            return entity.getId();
        } catch (Exception e) {
            System.err.println("----- 调用工具：【创建问卷】失败。错误信息：" + e.getMessage());
            e.printStackTrace();
            throw e; // 重新抛出异常以确保事务回滚
        }
    }

    @Tool(description = "创建问卷中的组件")
    @Transactional
    public String createVocComponents(@ToolParam(description = "问卷id") String vocId,
                                      @ToolParam(description = "组件列表") List<CreateComponentItem> componentList) {
        System.out.println("----- 调用工具：【创建问卷组件】。vocId: " + vocId );

        List<VocComponent> list = new ArrayList<>();
        componentList.forEach(component -> {
            VocComponent entity = new VocComponent();
            BeanUtils.copyProperties(component, entity);
            entity.setId(null);
            entity.setIsHidden((byte) 0);
            entity.setIsLocked((byte) 0);
            list.add(entity);
        });
        this.vocComponentService.getBaseMapper().insert(list);
        return vocId;
    }
}
