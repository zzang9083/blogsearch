package com.kakao.domain.model.blog;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class Blogs {

    private final Meta meta;
    private final List<Blog> blogs;

    public Blogs(Meta meta, List<Blog> blogs) {
        validation(meta, blogs);
        this.meta = meta;
        this.blogs = blogs;
    }

    public void add(Blogs targetBlogs) {
        blogs.addAll(targetBlogs.blogs);
    }

//    public Blogs postProcessBy(LocationsPostProcessor postProcessor) {
//        return postProcessor.postProcess(this);
//    }

    private void validation(Meta meta, List<Blog> locations) {
        if (meta == null || locations == null) {
            log.error("blogs cannot be null");
            throw new BusinessException(DomainErrorCode.INVALID_BLOG_ERROR);
        }
    }
}
