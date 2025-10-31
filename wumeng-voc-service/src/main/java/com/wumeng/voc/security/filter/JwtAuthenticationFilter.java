package com.wumeng.voc.security.filter;

import cn.hutool.core.text.AntPathMatcher;
import com.wumeng.voc.security.entity.SecurityUserDetails;
import com.wumeng.voc.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        // 放行 /login
        if ("/api/auth/login".equals(path)
                || "/api/auth/register".equals(path)
                || path.startsWith("/api/voc")
                || path.startsWith("/api/dict")
                || path.startsWith("/api/square")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("access_token");
        if (token == null) {
            throw new BadCredentialsException("token缺失");
        }

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
        if (userDetails == null) {
            throw new BadCredentialsException("token失效");
        }
        // 存入安全上下文
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
