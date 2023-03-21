package com.kakao.infrastructure.client.builder.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.kakao")
@Getter
@Setter
public class KakaoApiInfo {
    private String apiKey;
    private String host;
    private String path;

    public KakaoApiInfo(){}

    @Builder
    public KakaoApiInfo(String apiKey, String host, String path) {
        this.apiKey = apiKey;
        this.host = host;
        this.path = path;
    }
}
