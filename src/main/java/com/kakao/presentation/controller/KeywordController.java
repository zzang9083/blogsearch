package com.kakao.presentation.controller;

import com.kakao.application.dto.KeywordDTO;
import com.kakao.domain.error.BusinessException;
import com.kakao.domain.service.keyword.KeywordListUpService;
import com.kakao.presentation.error.PresentationErrorCode;
import com.kakao.presentation.response.KeywordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/keyword")
public class KeywordController {

    private final KeywordListUpService keywordListUpService;

    public KeywordController(KeywordListUpService keywordListUpService) {
        this.keywordListUpService = keywordListUpService;
    }

    @Operation(summary = "인기 검색어 목록 조회", description = "상위 10개의 인기 검색어를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료"),
            @ApiResponse(responseCode = "204", description = "검색된 결과가 존재하지 않습니다."),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, 조회 실패"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR, 조회 실패")})
    @GetMapping("/rank")
    public KeywordResponse getKeywordRank() {
        List<KeywordDTO> searchResult = keywordListUpService.listUpKeywords();
        if (searchResult == null || searchResult.isEmpty()) {
            throw new BusinessException(PresentationErrorCode.NO_CONTENT);
        }
        return KeywordResponse.of(searchResult);
    }
}
