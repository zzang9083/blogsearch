package com.kakao.application.dto;

import com.kakao.domain.model.blog.Meta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MetaDTO {

    @Schema(description = "검색된 문서수")
    private Integer totalCount; // 검색된 문서 수
    @Schema(description = "total_count 중 노출가능 문서수")
    private Integer pageableCount; // total_count 중 노출 가능 문서 수
    @Schema(description = "마지막 페이지 여부")
    private Boolean isEnd; //현재 페이지가 마지막 페이지인지 여부

    public MetaDTO() {}

    public MetaDTO(Integer totalCount, Integer pageableCount, Boolean isEnd) {
        this.totalCount = totalCount;
        this.pageableCount = pageableCount;
        this.isEnd = isEnd;
    }

    public static MetaDTO from(Meta meta) {
        return new MetaDTO(
                meta.getTotalCount(),
                meta.getPageableCount(),
                meta.getIsEnd()
        );
    }
}
