package com.wumeng.voc.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.IdEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 系统字典项表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-30 09:36:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_item")
@Schema(name = "SysDictItem", description = "系统字典项表")
public class SysDictItem extends IdEntity {

    @Serial
    private static final long serialVersionUID = 1556588210501750969L;

    /**
     * 字典项名称
     */
    @Schema(description = "字典项名称")
    private String dictLabel;

    /**
     * 字典项值
     */
    @Schema(description = "字典项值")
    private String dictValue;

    /**
     * 所述字典编码
     */
    @Schema(description = "所述字典编码")
    private String dictCode;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortNum;

    /**
     * 颜色
     */
    @Schema(description = "颜色")
    private String color;
}
