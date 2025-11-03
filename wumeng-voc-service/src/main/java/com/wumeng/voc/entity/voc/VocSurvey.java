package com.wumeng.voc.entity.voc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;

/**
 * <p>
 * 问卷信息表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 21:16:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("voc_survey")
@Schema(name = "VocSurvey", description = "问卷信息表")
public class VocSurvey extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 2258600923483292850L;

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * JS代码
     */
    @Schema(description = "JS代码")
    private String js;

    /**
     * CSS代码
     */
    @Schema(description = "CSS代码")
    private String css;

    /**
     * 是否发布
     */
    @Schema(description = "是否发布")
    private Byte isPublished;

    /**
     * 是否收藏
     */
    @Schema(description = "是否收藏")
    private Byte isStar;

    /**
     * 类别
     */
    @Schema(description = "类别")
    private String category;

    /**
     * 标签
     */
    @Schema(description = "标签")
    private String label;

    /**
     * 是否分享到广场
     */
    @Schema(description = "是否分享到广场")
    private Byte isShare;

    /**
     * 预估耗时，单位分钟
     */
    @Schema(description = "预估耗时，单位分钟")
    private Integer estimate;

}
