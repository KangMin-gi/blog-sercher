package com.flowerbun.blogsearcherapi.blog;

import com.flowerbun.blogsearcherapi.blog.dto.BlogRequest;
import com.flowerbun.blogsearcherapi.blog.dto.BlogResponse;
import com.flowerbun.blogsearcherapi.blog.dto.BlogResponse.SearchResults;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("blog")
@RestController
@RequiredArgsConstructor
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @GetMapping("/v1")
    public ResponseEntity<BlogResponse.SearchResults> searchBlogs(
            @Valid BlogRequest.Search searchParam
    ) {
        SearchResults searchResults = this.blogSearchService.searchBlogs(searchParam.toSearchParam());
        return ResponseEntity.ok(searchResults);
    }

}
