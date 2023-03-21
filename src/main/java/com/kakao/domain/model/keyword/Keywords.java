package com.kakao.domain.model.keyword;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import com.kakao.domain.model.keyword.function.KeywordsPostProcessor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Getter
@Slf4j
public class Keywords {
    private final List<Keyword> keywords;

    public Keywords(List<Keyword> keywords) {
        validation(keywords);
        this.keywords = keywords;
    }

    public Keywords postProcessBy(KeywordsPostProcessor postProcessor) {
        return postProcessor.postProcess(this);
    }

    private void validation(List<Keyword> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            log.error("keywords cannot be empty");
            throw new BusinessException(DomainErrorCode.INVALID_KEYWORDS_ERROR);
        }
    }
}
