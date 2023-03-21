package com.kakao.presentation.controller;

import com.kakao.application.dto.BlogsDTO;
import com.kakao.domain.error.BusinessException;
import com.kakao.domain.service.search.SearchService;
import com.kakao.domain.vo.Search;
import com.kakao.presentation.error.PresentationErrorCode;
import com.kakao.presentation.request.BlogSearchRequest;
import com.kakao.presentation.response.BlogSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class BlogController {

    private final SearchService searchService;

    public BlogController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Operation(summary = "블로그검색", description = "주어진 키워드로 블로그를 검색합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료"),
            @ApiResponse(responseCode = "204", description = "검색된 결과가 존재하지 않습니다."),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST, 조회 실패"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR, 조회 실패")})
    @GetMapping("/blog")
    public BlogSearchResponse searchBlog(@ModelAttribute BlogSearchRequest request) {
        BlogsDTO blogResult = searchService.searchBy(Search.of(request));
        if (blogResult == null) {
            throw new BusinessException(PresentationErrorCode.NO_CONTENT);
        }
        return BlogSearchResponse.of(blogResult);
    }


}
