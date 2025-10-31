package com.wumeng.voc.security.utils;

import com.alibaba.fastjson2.JSON;
import com.wumeng.common.vo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {

    public static void write(HttpServletResponse response, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter out = response.getWriter();
        out.println(message);
        out.flush();
        out.close();
    }

    public static void write(HttpServletResponse response, Result<?> result) throws IOException {
        write(response, JSON.toJSONString(result));
    }

    public static void write(HttpServletResponse response, Result<?> result, HttpStatus status) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        write(response, JSON.toJSONString(result));
    }
}
