package com.wumeng.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class MainGenerator {

    public static void main(String[] args) throws IOException {

        try (InputStream is = ClassLoader.getSystemResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("db_url");
            String username = properties.getProperty("db_username");
            String password = properties.getProperty("db_password");

            String pkgParent = properties.getProperty("pkg_parent") == null ? "com.wumeng" : properties.getProperty("pkg_parent");
            String pkgEntity = properties.getProperty("pkg_entity") == null ? "entity" : properties.getProperty("pkg_entity");
            String pkgMapper = properties.getProperty("pkg_mapper") == null ? "mapper" : properties.getProperty("pkg_mapper");
            String pkgService = properties.getProperty("pkg_service") == null ? "service" : properties.getProperty("pkg_service");
            String pkgServiceImpl = properties.getProperty("pkg_service_impl") == null ? "service.impl" : properties.getProperty("pkg_service_impl");
            String pkgXml = properties.getProperty("pkg_xml") == null ? "mapper.xml" : properties.getProperty("pkg_xml");
            String tables = properties.getProperty("tables");

            FastAutoGenerator.create(url, username, password)
                    .globalConfig(builder -> builder
                            .author("程序员优雅哥")
                            .outputDir(Paths.get(System.getProperty("user.dir")) + "/temp-gen-code")
                            .commentDate("yyyy-MM-dd HH:mm:ss")
                            .enableSpringdoc()
                    )
                    .packageConfig(builder -> builder
                            .parent(pkgParent)
                            .entity(pkgEntity)
                            .mapper(pkgMapper)
                            .service(pkgService)
                            .serviceImpl(pkgServiceImpl)
                            .xml(pkgXml)
                    )
                    .strategyConfig(builder -> {
                        if (StringUtils.hasText(tables)) {
                            builder.addInclude(tables.split(","));
                        }
                                builder
                                        .entityBuilder()
                                        .enableLombok();
                            }
                    )
                    .templateEngine(new FreemarkerTemplateEngine())
                    .execute();
        }




    }
}
