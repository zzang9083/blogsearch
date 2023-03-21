package com.kakao.infrastructure.client.builder;


import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.client.frame.ClientRequestFrame;
import com.kakao.infrastructure.entity.BlogEntities;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public abstract class ClientMonoBuilder {
    public Mono<BlogEntities> buildFor(Search search) {
        ClientRequestFrame clientRequestFrame = frameOf(search);
        return buildMono(clientRequestFrame);

    }
    private Mono<BlogEntities> buildMono(ClientRequestFrame request) {
        return  WebClient.builder()
                .baseUrl(request.getHost()).build().get()
                .uri(uriBuilder -> uriBuilder.path(request.getPath()).queryParams(request.getQueryParam()).build())
                .headers(header -> request.getHeaders().forEach(each -> header.add(each.getHeaderKey(), each.getHeaderValue())))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, res -> res.bodyToMono(String.class).map(IllegalStateException::new))
                .onStatus(HttpStatus::is5xxServerError, res -> res.bodyToMono(String.class).map(IllegalStateException::new))
                .bodyToMono(supportType())
                .map(BlogResponseBody::ofBlogs);
    }

    protected abstract ClientRequestFrame frameOf(Search search);
    protected abstract Class<? extends BlogResponseBody> supportType();
}
