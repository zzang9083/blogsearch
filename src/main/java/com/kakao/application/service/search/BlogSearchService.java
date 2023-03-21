package com.kakao.application.service.search;

import com.kakao.application.dto.BlogDTO;
import com.kakao.application.dto.BlogsDTO;
import com.kakao.application.dto.MetaDTO;
import com.kakao.domain.model.blog.Blogs;
import com.kakao.domain.repository.BlogRepository;
import com.kakao.domain.service.keyword.KeywordService;
import com.kakao.domain.service.search.SearchService;
import com.kakao.domain.vo.Search;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogSearchService implements SearchService {

    private final KeywordService keywordService;
    private final BlogRepository blogRepository;

    public BlogSearchService(KeywordService keywordService, BlogRepository blogRepository) {
        this.keywordService = keywordService;
        this.blogRepository = blogRepository;
    }


    @Override
    public BlogsDTO searchBy(Search search) {
        keywordService.record(search);
        Blogs blogs = blogRepository.findBy(search);

        MetaDTO metaDTO = MetaDTO.from(blogs.getMeta());
        List<BlogDTO> blogDTOList = blogs.getBlogs().stream().map(BlogDTO::from).collect(Collectors.toList());

        return new BlogsDTO(metaDTO, blogDTOList);
    }
}
