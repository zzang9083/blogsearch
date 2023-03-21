package com.kakao.infrastructure.client.builder.naver;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "api.naver")
@Getter @Setter
public class NaverApiInfo {
    private String host;
    private String path;
    private String clientId;
    private String clientSecret;

    public NaverApiInfo() {}
    @Builder
    public NaverApiInfo(String host, String path, String clientId, String clientSecret) {
        this.host = host;
        this.path = path;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
