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
 * 系统用户表
 * </p>
 *
 * @author 程序员优雅哥
 * @since 2025-10-26 16:59:23
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
@Schema(name = "SysUser", description = "系统用户表")
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 8475416286244605048L;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String gender;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String tel;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    private String refId;

    /**
     * 状态：1-有效，2-禁用
     */
    @Schema(description = "状态：1-有效，2-禁用")
    private Integer status;

}
