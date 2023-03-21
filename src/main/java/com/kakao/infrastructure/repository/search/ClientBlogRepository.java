package com.kakao.infrastructure.repository.search;

import com.kakao.domain.model.blog.Blogs;
import com.kakao.domain.repository.BlogRepository;
import com.kakao.domain.vo.Search;
import org.springframework.stereotype.Component;

@Component
public class ClientBlogRepository implements BlogRepository {

    private final BlogWebClients blogWebClients;

    public ClientBlogRepository(BlogWebClients blogWebClients) {
        this.blogWebClients = blogWebClients;
    }

    @Override
    public Blogs findBy(Search search) {
        return blogWebClients.requestAbout(search).convertBlogs();
    }
}
