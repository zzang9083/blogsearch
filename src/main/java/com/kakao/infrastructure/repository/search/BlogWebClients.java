package com.kakao.infrastructure.repository.search;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.builder.ClientMonoBuilder;
import com.kakao.infrastructure.client.builder.kakao.KakaoClientMonoBuilder;
import com.kakao.infrastructure.client.builder.naver.NaverClientMonoBuilder;
import com.kakao.infrastructure.entity.BlogEntities;
import com.kakao.infrastructure.entity.BlogMetaEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;


@Component
public class BlogWebClients implements Serializable {

    private final KakaoClientMonoBuilder kakaoClientMonoBuilder;
    private final NaverClientMonoBuilder naverClientMonoBuilder;

    public BlogWebClients(KakaoClientMonoBuilder kakaoClientMonoBuilder, NaverClientMonoBuilder naverClientMonoBuilder) {
        this.kakaoClientMonoBuilder = kakaoClientMonoBuilder;
        this.naverClientMonoBuilder = naverClientMonoBuilder;
    }

    // 결과 캐싱
    @Cacheable(value = "BlogEntities", cacheManager ="cacheManager" ,key = "#search.query +#search.sort + #search.page + #search.size")
    public BlogEntities requestAbout(Search search) {
        BlogEntities blogEntities = new BlogEntities(new BlogMetaEntity(), new ArrayList<>());
        try {
            blogEntities = run(kakaoClientMonoBuilder, search); //카카오 api call
        }
        catch (Exception e) {
            blogEntities = run(naverClientMonoBuilder, search); //naver api call
        }

        return blogEntities;
    }

    public BlogEntities run(ClientMonoBuilder builder, Search search) {
        return builder.buildFor(search)
                .share().block();
    }
}
