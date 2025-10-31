package com.wumeng.voc.entity.voc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.IdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 问卷组件表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("voc_component")
@Schema(name = "VocComponent", description = "问卷组件表")
public class VocComponent extends IdEntity {

    @Serial
    private static final long serialVersionUID = 3843378640122869284L;

    /**
     * 前端id
     */
    @Schema(description = "前端id")
    private String frontId;

    /**
     * 组件类型
     */
    @Schema(description = "组件类型")
    private String type;

    /**
     * 组件标题
     */
    @Schema(description = "组件标题")
    private String title;

    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏")
    private Byte isHidden;

    /**
     * 是否锁定
     */
    @Schema(description = "是否锁定")
    private Byte isLocked;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    private String surveyId;

    /**
     * 组件属性
     */
    @Schema(description = "组件属性")
    private String propsText;

    @Schema(description = "排序，从小到大")
    private Integer sortNum;
}
