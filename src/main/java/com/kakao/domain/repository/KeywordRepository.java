package com.kakao.domain.repository;

import com.kakao.domain.model.keyword.Keywords;
import com.kakao.domain.vo.Search;


public interface KeywordRepository {
    void save(Search search);
    Keywords findAll();
}
