package com.kakao.infrastructure.repository.keyword;

import com.kakao.domain.vo.Search;
import com.kakao.infrastructure.entity.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface JPAKeywordRepository extends JpaRepository<KeywordEntity, Long> {
    @Query("select k from KeywordEntity k where k.keyword = :#{#searchKeyword.query}")
    Optional<KeywordEntity> findBySearchKeyword(@Param("searchKeyword") Search searchKeyword);
}

