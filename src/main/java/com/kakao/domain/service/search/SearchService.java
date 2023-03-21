package com.kakao.domain.service.search;

import com.kakao.application.dto.BlogsDTO;
import com.kakao.domain.vo.Search;


public interface SearchService {
    BlogsDTO searchBy(Search search);
}
