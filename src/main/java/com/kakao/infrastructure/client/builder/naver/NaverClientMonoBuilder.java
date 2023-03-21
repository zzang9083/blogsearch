package com.kakao.infrastructure.client.builder.naver;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.builder.BlogResponseBody;
import com.kakao.infrastructure.client.builder.ClientMonoBuilder;
import com.kakao.infrastructure.client.frame.ClientRequestFrame;
import com.kakao.infrastructure.client.frame.ClientRequestFrame.ClientRequestHeader;
import com.kakao.infrastructure.client.frame.ClientRequestFrame.ClientRequestQueryParam;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NaverClientMonoBuilder extends ClientMonoBuilder {
    private static final String CLIENT_ID_HEADER = "X-Naver-Client-Id";
    private static final String CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";
    private static final String QUERY_KEY = "query";

    private static final String SORT_KEY = "sort";

    private static final String START_KEY = "start";

    private static final String DISPLAY_KEY = "display";

    private final NaverApiInfo apiInfo;

    public NaverClientMonoBuilder(NaverApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    @Override
    protected ClientRequestFrame frameOf(Search search) {
        return ClientRequestFrame.builder()
                .host(apiInfo.getHost())
                .path(apiInfo.getPath())
                .headers(Arrays.asList(
                        new ClientRequestHeader(CLIENT_ID_HEADER, apiInfo.getClientId()),
                        new ClientRequestHeader(CLIENT_SECRET_HEADER, apiInfo.getClientSecret())
                ))
                .params(Arrays.asList(
                        new ClientRequestQueryParam(QUERY_KEY, search.getQuery()),
                        new ClientRequestQueryParam(SORT_KEY, search.getSort()=="accuracy"?"sim":"date"),
                        new ClientRequestQueryParam(START_KEY, search.getPage().toString()),
                        new ClientRequestQueryParam(DISPLAY_KEY, search.getSize().toString()))
                )
                .build();
    }

    @Override
    protected Class<? extends BlogResponseBody> supportType() {
        return NaverResponseBody.class;
    }

}



