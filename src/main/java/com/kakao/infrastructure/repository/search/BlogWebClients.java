package com.kakao.infrastructure.repository.search;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.builder.ClientMonoBuilder;
import com.kakao.infrastructure.client.builder.kakao.KakaoClientMonoBuilder;
import com.kakao.infrastructure.client.builder.naver.NaverClientMonoBuilder;
import com.kakao.infrastructure.entity.BlogEntities;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.Serializable;



@Component
public class BlogWebClients implements Serializable {

    private final KakaoClientMonoBuilder kakaoClientMonoBuilder;
    private final NaverClientMonoBuilder naverClientMonoBuilder;


    public BlogWebClients(KakaoClientMonoBuilder kakaoClientMonoBuilder, NaverClientMonoBuilder naverClientMonoBuilder) {
        this.kakaoClientMonoBuilder = kakaoClientMonoBuilder;
        this.naverClientMonoBuilder = naverClientMonoBuilder;
    }

    // 결과 캐싱
    @Cacheable(value = "BlogEntities", cacheManager ="cacheManager", key = "#search.query +#search.sort + #search.page + #search.size")
    public BlogEntities requestAbout(Search search) {
        return runWithFallback(kakaoClientMonoBuilder, naverClientMonoBuilder, search);
    }

    public BlogEntities runWithFallback(ClientMonoBuilder primaryBuilder, ClientMonoBuilder fallbackBuilder, Search search) {
        try {
            return primaryBuilder.buildFor(search).share().block();
        } catch (Exception e) {
            // 예외가 발생하면 fallbackBuilder를 이용해 후행 API 수행
            return fallbackBuilder.buildFor(search).share().block();
        }
    }
}
