package com.wumeng.voc.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wumeng.components.mybatis.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户角色表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 16:59:23
 */
@Getter
@Setter
@ToString
@Builder
@TableName("sys_user_role")
@Schema(name = "SysUserRole", description = "系统用户角色表")
public class SysUserRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -5412941161199564182L;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;
}
