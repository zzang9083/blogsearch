package com.kakao.application.service.keyword;

import com.kakao.domain.repository.KeywordRepository;
import com.kakao.domain.vo.Search;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  검색 기록 트랜잭션을 수행하는 기본 서비스
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TransactionalRecordService {
    private final KeywordRepository keywordRepository;

    public TransactionalRecordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public void recording(Search search) {
        keywordRepository.save(search);
    }
}
