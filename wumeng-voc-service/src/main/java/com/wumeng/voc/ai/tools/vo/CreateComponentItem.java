package com.wumeng.voc.ai.tools.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateComponentItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 2266367289636392964L;

    @ToolParam(description = "组件id", required = false)
    private String id;

    @ToolParam(description = "组件类型")
    private String type;

    @ToolParam(description = "组件标题")
    private String title;

    @ToolParam(description = "问卷id")
    private String surveyId;

    @ToolParam(description = "排序")
    private Integer sortNum;

    @ToolParam(description = "组件属性")
    private String propsText;
}
