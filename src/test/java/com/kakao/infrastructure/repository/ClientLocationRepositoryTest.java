//package com.kakao.infrastructure.repository;
//
//
//
//import com.kakao.domain.model.blog.Blogs;
//import com.kakao.domain.vo.Search;
//import com.kakao.infrastructure.client.builder.kakao.KakaoApiInfo;
//import com.kakao.infrastructure.client.builder.kakao.KakaoClientMonoBuilder;
//import com.kakao.infrastructure.repository.search.BlogWebClients;
//import com.kakao.infrastructure.repository.search.ClientBlogRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ClientLocationRepositoryTest {
//
//    BlogWebClients blogWebClients = new BlogWebClients(Arrays.asList(
//            new KakaoClientMonoBuilder(kakaoApiInfo()),
//       new NaverClientMonoBuilder(naverApiInfo())
//    ));
//
//    @Test
//    @DisplayName("Kakao 장애시 Naver API 호출 테스트")
//    void client_별_요청_처리_결과_반환_테스트() {
//
//        ClientBlogRepository clientBlogRepository = new ClientBlogRepository(blogWebClients);
//        Blogs blogs = clientBlogRepository.findBy(new Search("피자", "accuracy", 1, 10));
//
//        assertThat(blogs.getBlogs()).hasSize(10);
//    }
//
//    private KakaoApiInfo kakaoApiInfo() {
//        return KakaoApiInfo.builder()
//                .host("https://dapi.kakao.com")
//                .path("/v2/search/blog")
//                .apiKey("1e86f3354d827c03cad979c213776bf1")
//                .build();
//    }
//
////    private NaverApiInfo naverApiInfo() {
////        return NaverApiInfo.builder()
////                .host("https://openapi.naver.com")
////                .path("/v1/search/local.json")
////                .clientId("_8o4EDT1hTGUY4iPKU90")
////                .clientSecret("W8zCY43K_R")
////                .displayCount("5")
////                .build();
////    }
//}