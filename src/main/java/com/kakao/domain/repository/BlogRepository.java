package com.kakao.domain.repository;

import com.kakao.domain.model.blog.Blogs;
import com.kakao.domain.vo.Search;

public interface BlogRepository {
    Blogs findBy(Search search);
}
