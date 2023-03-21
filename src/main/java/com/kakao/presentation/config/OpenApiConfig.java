package com.kakao.presentation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
        Info info = new Info()
                .title("블로그 검색 서비스")
                .version(springdocVersion)
                .description("주어진 키워드로 블로그를 검색하고 인기 검색어 목록을 조회합니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
