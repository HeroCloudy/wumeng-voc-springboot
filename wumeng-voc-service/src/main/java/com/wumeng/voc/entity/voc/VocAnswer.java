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
 * @since 2025-10-28 21:52:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("voc_answer")
@Schema(name = "VocAnswer", description = "问卷回答表")
public class VocAnswer extends IdEntity {

    @Serial
    private static final long serialVersionUID = 9069499643347447213L;

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    private String surveyId;

    /**
     * 提交人
     */
    @Schema(description = "提交人")
    private String submitter;

    /**
     * 答案JSON
     */
    @Schema(description = "答案JSON")
    private String jsonValue;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
