package com.kakao.application.service.keyword;

import com.kakao.application.dto.KeywordDTO;
import com.kakao.domain.model.keyword.Keywords;
import com.kakao.domain.model.keyword.function.KeywordsPostProcessor;
import com.kakao.domain.repository.KeywordRepository;
import com.kakao.domain.service.keyword.KeywordListUpService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class KeywordRankListUpService implements KeywordListUpService {

    private final KeywordRepository keywordRepository;
    private final KeywordsPostProcessor keywordsPostProcessor;

    public KeywordRankListUpService(KeywordRepository keywordRepository, KeywordsPostProcessor keywordsPostProcessor) {
        this.keywordRepository = keywordRepository;
        this.keywordsPostProcessor = keywordsPostProcessor;
    }

    @Override
    public List<KeywordDTO> listUpKeywords() {
        Keywords keywords = keywordRepository.findAll();

        return keywords.postProcessBy(keywordsPostProcessor).getKeywords()
                .stream().map(KeywordDTO::from).collect(toList());
    }
}
