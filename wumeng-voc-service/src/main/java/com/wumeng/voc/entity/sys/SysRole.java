package com.wumeng.voc.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 16:59:23
 */
@Getter
@Setter
@ToString
@TableName("sys_role")
@Schema(name = "SysRole", description = "系统角色表")
public class SysRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 6090930255408252112L;

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
