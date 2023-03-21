package com.kakao.application.dto;

import com.kakao.domain.model.keyword.Keyword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeywordDTO {
    @Schema(description = "키워드")
    private String keyword;
    @Schema(description = "검색횟수")
    private Long searchCount;
    public KeywordDTO(){}

    public KeywordDTO(String keyword, Long searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public static KeywordDTO from(Keyword keyword) {
        return new KeywordDTO(keyword.getKeyword(), keyword.getSearchCount());
    }
}
