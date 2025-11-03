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
public class CreateSurveyReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1520190764237278196L;

    @ToolParam(required = false, description = "问卷id")
    private String id;

    @ToolParam(description = "问卷标题")
    private String title;

    @ToolParam(description = "问卷描述")
    private String description;

    @ToolParam(description = "问卷类型")
    private String category;

    @ToolParam(description = "问卷标签")
    private String label;

    @ToolParam(description = "预估耗时，单位分钟")
    private Integer estimate;

    @ToolParam(description = "当前的用户id")
    private String createBy;
}
