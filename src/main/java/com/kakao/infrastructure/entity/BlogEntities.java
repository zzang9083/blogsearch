package com.kakao.infrastructure.entity;

import com.kakao.domain.model.blog.Blogs;
import com.kakao.domain.model.blog.Meta;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogEntities {

    private BlogMetaEntity blogMetaEntity;
    private List<BlogEntity> blogEntities;

    public BlogEntities(BlogMetaEntity blogMetaEntity, List<BlogEntity> blogEntities) {
        this.blogMetaEntity = blogMetaEntity;
        this.blogEntities = blogEntities;
    }

    public void add(BlogEntities targetEntities) {
        blogMetaEntity = targetEntities.getBlogMetaEntity();
        blogEntities.addAll(targetEntities.getBlogEntities());
    }

    public Blogs convertBlogs() {
        return new Blogs(new Meta(blogMetaEntity.getPageableCount(),blogMetaEntity.getPageableCount(),blogMetaEntity.getIsEnd()),
                blogEntities.stream().map(BlogEntity::convert).collect(toList()));
    }
}
