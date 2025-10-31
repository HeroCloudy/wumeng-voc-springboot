package com.wumeng.components.mybatis.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DeletedEntity extends IdEntity {

    @Serial
    private static final long serialVersionUID = -7533694146136321940L;

    /**
     * 是否删除 0未删除，1已删除
     */
    @Schema(description = "是否删除 0未删除，1已删除")
    private Byte isDeleted;
}
