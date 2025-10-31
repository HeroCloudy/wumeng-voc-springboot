package com.wumeng.components.doc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ConfigurationProperties(prefix = "wumeng.doc")
@Configuration
public class DocProperties {

    private Info info = new Info();
    private ExternalDocumentation externalDocs = new ExternalDocumentation();

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class Info extends io.swagger.v3.oas.models.info.Info {

        private License license = new License();
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class License extends io.swagger.v3.oas.models.info.License {
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class ExternalDocumentation extends io.swagger.v3.oas.models.ExternalDocumentation {
    }

}
