package com.kakao.infrastructure.entity;


import com.kakao.domain.model.blog.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BlogMetaEntity {

    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;



    @Builder
    public BlogMetaEntity(Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }

    public Meta convert() {
        return new Meta(
                totalCount,
                pageableCount,
                isEnd
        );
    }
}
