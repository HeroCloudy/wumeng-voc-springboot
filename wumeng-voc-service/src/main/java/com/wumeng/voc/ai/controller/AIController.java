package com.wumeng.voc.ai.controller;

import com.wumeng.voc.ai.config.AIConstants;
import com.wumeng.voc.ai.utils.UserContextHolder;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import com.wumeng.voc.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("ai")
public class AIController {

    private final ChatClient chatClient;

    private final RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/demo-str")
    public String chat(String prompt) {
        return chatClient
                .prompt(prompt) // 传入user提示词
                .call() // 同步请求，会等待AI全部输出完才返回结果
                .content(); //返回响应内容
    }

    @GetMapping("/stream")
    public Flux<String> stream(String prompt, @RequestHeader(value = "access_token", required = false) String token) {
        System.out.println("--- token: " + token);
        String nickname = null;
        if (StringUtils.hasText(token)) {
            // 解析 token
            String subject = "";
            try {
                Claims claims = JwtUtils.parseToken(token);
                subject = claims.getSubject();
            } catch (Exception e) {
                throw new BadCredentialsException("token错误");
            }

            // 从 redis 获取用户
            SecurityUserDetails userDetails = (SecurityUserDetails) this.redisTemplate.opsForValue().get(subject);
            if (userDetails != null) {
                nickname = userDetails.getUser().getNickname();

                // 存入安全上下文
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 构建提示词
        StringBuilder promptBuilder = new StringBuilder();

        // 添加用户登录状态信息
        if (nickname != null) {
            promptBuilder.append("用户已登录，昵称是：").append(nickname).append("。请用用户昵称称呼用户。\n");
        } else {
            promptBuilder.append("用户未登录。\n");
        }

        // 3. 添加用户问题
        promptBuilder.append("用户问题：").append(prompt);

        System.out.println(promptBuilder.toString());

        // 使用Message API构建请求
        return chatClient.prompt(promptBuilder.toString())
                .stream()
                .content();
//                .call(
//                        ChatRequest.builder()
//                                .messages(messages)
//                                .build()
//                )
//                .content();

//        return chatClient
//                .prompt(new Prompt(prompt))
//                .stream()
//                .content(); //返回响应内容

    }
}
