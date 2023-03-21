package com.kakao.application.service.keyword.function;

import com.kakao.domain.model.keyword.Keyword;
import com.kakao.domain.model.keyword.Keywords;
import com.kakao.domain.model.keyword.function.KeywordsPostProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class KeywordsRankProcessor implements KeywordsPostProcessor {
    private static final int LIMIT_COUNT = 10;
    @Override
    public Keywords postProcess(Keywords originKeywords) {

        List<Keyword> collect = originKeywords.getKeywords()
                .stream()
                .sorted((o1, o2) -> o2.getSearchCount().compareTo(o1.getSearchCount()))
                .limit(LIMIT_COUNT)
                .collect(toList());

        return new Keywords(collect);
    }
}
