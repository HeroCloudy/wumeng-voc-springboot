package com.wumeng.voc.security.config;

import com.wumeng.common.vo.Result;
import com.wumeng.voc.security.filter.JwtAuthenticationFilter;
import com.wumeng.voc.security.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Slf4j
@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final UserDetailsPasswordService userDetailsPasswordService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(this.userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsPasswordService(this.userDetailsPasswordService);
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> {
                    // 认证异常处理
                    exception.authenticationEntryPoint((request, response, authException) -> {
                        Result<String> result = Result.error(401, authException.getMessage());
                        log.error("认证异常: {}", result, authException);
                        ResponseUtils.write(response, result, HttpStatus.UNAUTHORIZED);
                    });
                    // 授权异常处理
                    exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                        log.error("授权异常", accessDeniedException);
                        ResponseUtils.write(response, Result.error(403, accessDeniedException.getMessage()), HttpStatus.FORBIDDEN);
                    });
                })
                .addFilterBefore(this.jwtAuthenticationFilter, AuthorizationFilter.class);
        return http.build();
    }
}
