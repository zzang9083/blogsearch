package com.kakao.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BlogsDTO {
    private MetaDTO meta; // 메타데이터
    private List<BlogDTO> blogs; // 결과데이터

    public BlogsDTO() {
    }

    public BlogsDTO(MetaDTO meta, List<BlogDTO> blogs) {
        this.meta = meta;
        this.blogs = blogs;
    }

    public static BlogsDTO from(MetaDTO meta, List<BlogDTO> blogs) {
        return new BlogsDTO(
                meta, blogs
        );
    }

}
