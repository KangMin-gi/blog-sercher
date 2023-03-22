package com.flowerbun.blogsearcherapi.blog.dto;

import com.flowerbun.blogsearcher.external.core.blog.Blog;
import com.flowerbun.blogsearcher.external.core.blog.BlogDocument;
import com.flowerbun.blogsearcher.external.core.blog.BlogMeta;
import com.flowerbun.blogsearcherapi.dto.Page;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class BlogResponse {

    @Getter
    public static class SearchResults {

        private Page.Response page;
        private List<SearchResult> blogs;

        public SearchResults(Blog blog) {
            BlogMeta meta = blog.getMeta();
            this.page = Page.Response.of(meta.getTotalCount(), meta.getPageableCount());
            this.blogs = blog.getDocuments().stream()
                    .map(SearchResult::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class SearchResult {
        private String blogName;
        private String url;
        private String content;
        private LocalDateTime dateTime;

        public SearchResult(BlogDocument blogDocument) {
            this.blogName = blogDocument.getBlogName();
            this.url = blogDocument.getUrl();
            this.content = blogDocument.getContents();
            this.dateTime = blogDocument.getDatetime();
        }
    }
}
