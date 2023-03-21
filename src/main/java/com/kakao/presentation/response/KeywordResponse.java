package com.kakao.presentation.response;

import com.kakao.application.dto.KeywordDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KeywordResponse {

    @Schema(description = "인기검색어 검색결과")
    private List<KeywordDTO> keywords;

    private KeywordResponse(List<KeywordDTO> keywords) {
        this.keywords = keywords;
    }

    public static KeywordResponse of(List<KeywordDTO> keywords) {
        return new KeywordResponse(keywords);
    }
}
