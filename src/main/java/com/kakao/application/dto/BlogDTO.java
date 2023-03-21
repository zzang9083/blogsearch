package com.kakao.application.dto;

import com.kakao.domain.model.blog.Blog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BlogDTO {

    @Schema(description = "블로그글 제목")
    private String title; // 블로그 글 제목

    @Schema(description = "블로그글 요약")
    private String contents; // 블로그 글 요약

    @Schema(description = "블로그글 URL")
    private String url; // 블로그 글 URL

    @Schema(description = "블로그의 이름")
    private String blogname; // 블로그의 이름

    @Schema(description = "대표 미리보기 이미지 URL")
    private String thumbnail; // 검색 시스템에서 추출한 대표 미리보기 이미지 URL

    @Schema(description = "블로그글 작성시간")
    private String datetime; //블로그 글 작성시간


    public BlogDTO() {}

    public BlogDTO(String title, String contents, String url, String blogname, String thumbnail, String datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }


    public static BlogDTO from(Blog blog) {
        return new BlogDTO(
                blog.getTitle().getTitle(),
                blog.getContents().getContents(),
                blog.getUrl().getUrl(),
                blog.getBlogname(),
                blog.getThumbnail(),
                blog.getDatetime()
        );
    }
}
