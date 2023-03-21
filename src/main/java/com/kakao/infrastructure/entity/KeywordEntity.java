package com.kakao.infrastructure.entity;

import com.kakao.domain.model.keyword.Keyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword", nullable = false, unique = true)
    private String keyword;

    @Column(name = "search_count", nullable = false)
    private Long searchCount;

    @Builder
    public KeywordEntity(Long id, String keyword, Long searchCount) {
        this.id = id;
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    @PrePersist
    private void initSearchCount() {
        this.searchCount = 1L;
    }

    public void increaseSearchCount() {
        searchCount += 1;
    }

    public Keyword convert() {
        return new Keyword(id, keyword, searchCount);
    }
}
