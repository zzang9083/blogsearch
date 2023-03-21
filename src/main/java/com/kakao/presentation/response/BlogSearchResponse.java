package com.kakao.presentation.response;

import com.kakao.application.dto.BlogDTO;
import com.kakao.application.dto.BlogsDTO;
import com.kakao.application.dto.MetaDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BlogSearchResponse {

    @Schema(description = "메타데이터")
    private MetaDTO meta;

    @Schema(description = "키워드 검색결과")
    private List<BlogDTO> blogs;

    private BlogSearchResponse(MetaDTO meta, List<BlogDTO> blogs) {
        this.meta = meta;
        this.blogs = blogs;
    }

    public static BlogSearchResponse of(BlogsDTO blogs) {
        return new BlogSearchResponse(blogs.getMeta(), blogs.getBlogs());
    }

}
