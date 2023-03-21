package com.kakao.domain.vo;

import ch.qos.logback.core.CoreConstants;
import com.kakao.application.dto.BlogsDTO;
import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import com.kakao.presentation.request.BlogSearchRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class Search {
    private final String query;

    private String sort;

    private Integer page;

    private Integer size;

    public Search(String query, String sort, Integer page, Integer size) {
        validation(query);
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }
    private void validation(String query) {
        if (query == null || query.isEmpty()) {
            throw new BusinessException(DomainErrorCode.CANNOT_SEARCH_NULL_ERROR);
        }
    }

    public static Search of(BlogSearchRequest req) {
        return new Search(req.getQuery(), req.getSort(), req.getPage(), req.getSize());
    }
}
