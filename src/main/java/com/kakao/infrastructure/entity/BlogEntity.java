package com.kakao.infrastructure.entity;


import com.kakao.domain.model.blog.Blog;
import com.kakao.domain.model.blog.Contents;
import com.kakao.domain.model.blog.Title;
import com.kakao.domain.model.blog.Url;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogEntity {

    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String datetime;

    @Builder
    public BlogEntity(String title, String contents, String url, String blogname, String thumbnail, String datetime) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.blogname = blogname;
        this.thumbnail = thumbnail;
        this.datetime = datetime;
    }

    public Blog convert() {
        return new Blog(
                new Title(title),
                new Contents(contents),
                new Url(url),
                blogname,
                thumbnail,
                datetime
        );
    }
}
