package com.kakao.infrastructure.repository.keyword;

import com.kakao.domain.model.keyword.Keywords;
import com.kakao.domain.repository.KeywordRepository;
import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.entity.KeywordEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Slf4j
@Repository
public class DBKeywordRepository implements KeywordRepository {

    private final JPAKeywordRepository jpaRepository;

    public DBKeywordRepository(JPAKeywordRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Search search) {
        jpaRepository.findBySearchKeyword(search)
                .ifPresentOrElse(KeywordEntity::increaseSearchCount,
                        () -> jpaRepository.save(KeywordEntity.builder().keyword(search.getQuery()).build()));

    }

    @Override
    public Keywords findAll() {
        List<KeywordEntity> allKeywords = jpaRepository.findAll();
        return new Keywords(allKeywords.stream().map(KeywordEntity::convert).collect(toList()));
    }
}
