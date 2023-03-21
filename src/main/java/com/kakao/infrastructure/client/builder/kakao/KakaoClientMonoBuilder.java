package com.kakao.infrastructure.client.builder.kakao;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.builder.BlogResponseBody;
import com.kakao.infrastructure.client.builder.ClientMonoBuilder;
import com.kakao.infrastructure.client.frame.ClientRequestFrame;
import com.kakao.infrastructure.client.frame.ClientRequestFrame.ClientRequestHeader;
import com.kakao.infrastructure.client.frame.ClientRequestFrame.ClientRequestQueryParam;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KakaoClientMonoBuilder extends ClientMonoBuilder {

    private static final String AUTH_PREFIX = "KakaoAK ";
    private static final String AUTH_HEADER = "Authorization";
    private static final String QUERY_KEY = "query";
    private static final String SORT_KEY = "sort";
    private static final String PAGE_KEY = "page";
    private static final String SIZE_KEY = "size";

    private final KakaoApiInfo apiInfo;

    public KakaoClientMonoBuilder(KakaoApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    @Override
    protected ClientRequestFrame frameOf(Search search) {
        return ClientRequestFrame.builder()
                .host(apiInfo.getHost())
                .path(apiInfo.getPath())
                .headers(Arrays.asList(new ClientRequestHeader(AUTH_HEADER, AUTH_PREFIX + apiInfo.getApiKey())))
                .params(Arrays.asList(
                        new ClientRequestQueryParam(QUERY_KEY, search.getQuery()),
                        new ClientRequestQueryParam(SORT_KEY, search.getSort()),
                        new ClientRequestQueryParam(PAGE_KEY, search.getPage().toString()),
                        new ClientRequestQueryParam(SIZE_KEY, search.getSize().toString()))
                )
                .build();
    }

    @Override
    protected Class<? extends BlogResponseBody> supportType() {
        return KakaoResponseBody.class;
    }
}
