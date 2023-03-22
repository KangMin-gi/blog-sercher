package com.flowerbun.blogsearcherapi.blog;

import com.flowerbun.blogsearcher.external.core.BlogSearcher;
import com.flowerbun.blogsearcher.external.core.blog.Blog;
import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.entity.NoBlogContentException;
import com.flowerbun.blogsearcherapi.blog.dto.BlogResponse;
import com.flowerbun.blogsearcherapi.blog.dto.BlogResponse.SearchResults;
import com.flowerbun.blogsearcherapi.exception.CustomerException;
import com.flowerbun.blogsearcherapi.exception.Errors;
import com.flowerbun.blogsearcherapi.querycount.BlogSearchQueryCalculateService;
import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto.SearchQueryCounting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogSearchService {

    private final BlogSearcher kakaoBlogSearcher;
    private final BlogSearchQueryCalculateService calculateService;

    @Transactional
    public BlogResponse.SearchResults searchBlogs(BlogSearchParam blogSearchParam) {
        try {
            Blog blog = this.kakaoBlogSearcher.search(blogSearchParam)
                    .blog();
            this.calculateService.calculateQueryCount(
                    new SearchQueryCounting(blogSearchParam.getQuery())
            );
            return new SearchResults(blog);
        } catch (NoBlogContentException nbce) {
            log.error("[Blog Loading Error] message = {}", nbce, nbce.getMessage());
            throw new CustomerException(Errors.BLOG_SEARCH);
        }
    }
}
