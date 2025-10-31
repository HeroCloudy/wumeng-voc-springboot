package com.wumeng.voc.entity.voc;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.IdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * <p>
 * 问卷回答表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-28 16:58:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("voc_answer_item")
@Schema(name = "VocAnswerItem", description = "问卷回答明细表")
public class VocAnswerItem extends IdEntity {

    @Serial
    private static final long serialVersionUID = -4213201358187360082L;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    private String surveyId;

    /**
     * 回答id
     */
    @Schema(description = "回答id")
    private String answerId;

    /**
     * 提交人
     */
    @Schema(description = "提交人")
    private String submitter;

    /**
     * 问卷组件id
     */
    @Schema(description = "问卷组件id")
    private String componentId;

    /**
     * 答案
     */
    @Schema(description = "答案")
    private String value;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
