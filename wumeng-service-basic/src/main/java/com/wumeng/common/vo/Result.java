package com.wumeng.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回结果实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "通用响应结构")
public class Result<T> implements Serializable {

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer ERROR_CODE = 500;

    @Schema(description = "响应码，200成功")
    private Integer code;

    @Schema(description = "响应消息")
    private String msg;

    @Schema(description = "响应数据")
    private T data;

    public boolean isSuccess() {
        return Objects.equals(SUCCESS_CODE, code);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(SUCCESS_CODE, "success", data);
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> error(String msg) {
        return error(ERROR_CODE, msg);
    }

    public static <T> Result<T> error() {
        return error(ERROR_CODE, "System Error");
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && result.isSuccess();
    }
}
