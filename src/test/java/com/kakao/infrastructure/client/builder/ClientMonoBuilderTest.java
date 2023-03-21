package com.kakao.infrastructure.client.builder;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.builder.kakao.KakaoApiInfo;
import com.kakao.infrastructure.client.builder.kakao.KakaoClientMonoBuilder;
import com.kakao.infrastructure.entity.BlogEntities;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;


class ClientMonoBuilderTest {

    @Test
    @DisplayName("카카오 API 호출 테스트")
    void kakaoTest() {
        KakaoApiInfo kakaoApiInfo = kakaoApiInfo();
        ClientMonoBuilder clientMonoBuilder = new KakaoClientMonoBuilder(kakaoApiInfo);

        Mono<BlogEntities> result = clientMonoBuilder.buildFor(new Search("피자","accuracy",1,10));

        BlogEntities block = result.block();
        Assertions.assertThat(block).isNotNull();
    }

    private KakaoApiInfo kakaoApiInfo() {
        return KakaoApiInfo.builder()
                .host("https://dapi.kakao.com")
                .path("/v2/search/blog")
                .apiKey("1e86f3354d827c03cad979c213776bf1")
                .build();
    }

}