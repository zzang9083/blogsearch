package com.kakao.presentation.request;

import com.kakao.application.dto.BlogDTO;
import com.kakao.application.dto.BlogsDTO;
import com.kakao.application.dto.MetaDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "블로그검색 요청DTO")
@Getter
@Setter
@NoArgsConstructor
public class BlogSearchRequest {
    @NotEmpty @NotBlank @NotNull
    @Schema(description = "검색질의어", defaultValue = "테스트")
    private String query;
    @Schema(description = "정렬방식", defaultValue = "accuracy", allowableValues = {"accuracy", "recency"})
    private String sort;

    @Schema(description = "결과페이지번호", defaultValue = "1")
    private Integer page;
    @Schema(description = "한페이지당문서수", defaultValue = "10")
    private Integer size;

    public BlogSearchRequest(String query, String sort, Integer page, Integer size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

}
