package com.wumeng.voc.ai.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class VocTools {

    @Tool(description = "")
    public String create() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
