package com.kakao.infrastructure.client.builder.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakao.infrastructure.client.builder.BlogResponseBody;
import com.kakao.infrastructure.entity.BlogEntities;
import com.kakao.infrastructure.entity.BlogEntity;
import com.kakao.infrastructure.entity.BlogMetaEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter @Setter
public class KakaoResponseBody implements BlogResponseBody {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("documents")
    private List<Document> documents;

    @Override
    public BlogEntities ofBlogs() {
        return new BlogEntities(meta.ofMeta(), documents.stream().map(Document::ofBlog).collect(toList()));
    }

    @Getter @Setter
    public static class Meta {
        @JsonProperty("total_count")
        private Integer totalCount;
        @JsonProperty("pageable_count")
        private Integer pageableCount;
        @JsonProperty("is_end")
        private Boolean isEnd;

        public BlogMetaEntity ofMeta() {
            return BlogMetaEntity.builder()
                    .totalCount(totalCount)
                    .pageableCount(pageableCount)
                    .isEnd(isEnd)
                    .build();
        }
    }


    @Getter @Setter
    public static class Document {
        @JsonProperty("title")
        private String title;
        @JsonProperty("contents")
        private String contents;
        @JsonProperty("url")
        private String url;
        @JsonProperty("blogname")
        private String blogname;
        @JsonProperty("thumbnail")
        private String thumbnail;
        @JsonProperty("datetime")
        private String datetime;


        public BlogEntity ofBlog() {
            return BlogEntity.builder()
                    .title(title)
                    .contents(contents)
                    .url(url)
                    .blogname(blogname)
                    .thumbnail(thumbnail)
                    .datetime(datetime)
                    .build();
        }
    }

}
