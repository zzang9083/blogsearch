package com.kakao.domain.model.blog;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
public class Blog {

    private final Title title; // 블로그 글 제목

    private final Contents contents; // 블로그 글 요약

    private final Url url; // 블로그 글 URL

    private final String blogname; // 블로그의 이름

    private final String thumbnail; // 검색 시스템에서 추출한 대표 미리보기 이미지 URL

    private final String datetime; //블로그 글 작성시간

    public Blog(Title title, Contents contents, Url url, String blogname, String thumbnail, String datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }

    private void validation(Title title, Contents contents, Url url) {
        if (title == null || contents == null || url == null) {
            log.error("Blog arguments cannot be null");
            throw new BusinessException(DomainErrorCode.INVALID_BLOG_ERROR);
        }
    }

}
