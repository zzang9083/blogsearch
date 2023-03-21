package com.kakao.domain.model.keyword.function;

import com.kakao.domain.model.keyword.Keywords;

/**
 * Keywords에 대한 후처리 작업을 위한 타입
 */

public interface KeywordsPostProcessor {
    Keywords postProcess(final Keywords originKeywords);
}
