package com.wumeng.common.exception;

import com.wumeng.common.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class, BindException.class})
    public Result<?> validateException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        String msg = null;
        if (e instanceof ConstraintViolationException constraintViolationException) {
            Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
            ConstraintViolation<?> next = violations.iterator().next();
            msg = next.getMessage();
        } else if (e instanceof BindException bindException) {
            msg = Objects.requireNonNull(bindException.getBindingResult().getFieldError()).getDefaultMessage();
        }
        return Result.error(msg);
    }

    @ExceptionHandler(value = CommonException.class)
    public Result<?> handleCommonException(CommonException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}
