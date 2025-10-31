package com.wumeng.voc.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.DeletedEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 系统字典表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-30 09:36:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict")
@Schema(name = "SysDict", description = "系统字典表")
public class SysDict extends DeletedEntity {

    @Serial
    private static final long serialVersionUID = -5492653011628274065L;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
}
