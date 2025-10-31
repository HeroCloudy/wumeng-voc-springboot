package com.wumeng.components.minio.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "wumeng.minio")
public class MinioProperties {

    /**
     * minio地址+端口号
     */
    private String endpoint;
    /**
     * minio用户名
     */
    private String accessKey;
    /**
     * minio密码
     */
    private String secretKey;
}
