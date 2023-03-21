package com.kakao.domain.model.blog;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
public class Meta {
    private final Integer totalCount; // 검색된 문서 수

    private final Integer pageableCount; // total_count 중 노출 가능 문서 수

    private final Boolean isEnd; //현재 페이지가 마지막 페이지인지 여부

    public Meta(Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }
}
