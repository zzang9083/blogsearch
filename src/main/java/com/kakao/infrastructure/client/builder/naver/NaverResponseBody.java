package com.kakao.infrastructure.client.builder.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kakao.infrastructure.client.builder.BlogResponseBody;
import com.kakao.infrastructure.client.builder.kakao.KakaoResponseBody;
import com.kakao.infrastructure.entity.BlogEntities;
import com.kakao.infrastructure.entity.BlogEntity;
import com.kakao.infrastructure.entity.BlogMetaEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Getter
@Setter
public class NaverResponseBody implements BlogResponseBody {

    @JsonProperty("channel")
    private Channel channel;


    @Override
    public BlogEntities ofBlogs() {
        return new BlogEntities(channel.ofMeta(), channel.items.stream().map(Channel.Item::ofBlog).collect(toList()));
    }

    @Getter @Setter
    public static class Channel {
        @JsonProperty("total")
        private Integer totalCount;

        @JsonProperty("display")
        private Integer pageableCount;
        //@JsonProperty("is_end")
        //private Boolean isEnd;

        private List<Item> items;

        public BlogMetaEntity ofMeta() {
            return BlogMetaEntity.builder()
                    .totalCount(totalCount)
                    .pageableCount(pageableCount)
                    .isEnd(false)
                    .build();
        }

        @Getter @Setter
        public static class Item {
            @JsonProperty("title")
            private String title;
            @JsonProperty("description")
            private String contents;
            @JsonProperty("link")
            private String url;
            @JsonProperty("bloggername")
            private String blogname;
            @JsonProperty("bloggerlink")
            private String thumbnail;
            @JsonProperty("postdate")
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



}
