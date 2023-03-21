package com.kakao.infrastructure.client.frame;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;


public class ClientRequestFrame {
    @Getter
    private final String host;
    @Getter
    private final String path;
    @Getter
    private final List<ClientRequestHeader> headers;
    private final List<ClientRequestQueryParam> params;

    public MultiValueMap<String, String> getQueryParam() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        params.forEach(param -> multiValueMap.add(param.queryKey, param.queryValue));
        return multiValueMap;
    }

    @Builder
    public ClientRequestFrame(
            String host,
            String path,
            List<ClientRequestHeader> headers,
            List<ClientRequestQueryParam> params
    ) {
        this.host = host;
        this.path = path;
        this.headers = headers;
        this.params = params;
    }

    @Getter
    public static class ClientRequestHeader {
        private final String headerKey;
        private final String headerValue;

        @Builder
        public ClientRequestHeader(String headerKey, String headerValue) {
            this.headerKey = headerKey;
            this.headerValue = headerValue;
        }
    }

    @Getter
    public static class ClientRequestQueryParam {
        private final String queryKey;
        private final String queryValue;

        @Builder
        public ClientRequestQueryParam(String queryKey, String queryValue) {
            this.queryKey = queryKey;
            this.queryValue = queryValue;
        }
    }
}
