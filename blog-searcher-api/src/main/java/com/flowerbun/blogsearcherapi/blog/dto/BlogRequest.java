package com.flowerbun.blogsearcherapi.blog.dto;

import com.flowerbun.blogsearcher.external.core.blog.BlogPaging;
import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.blog.BlogSort;
import com.flowerbun.blogsearcherapi.exception.CustomerException;
import com.flowerbun.blogsearcherapi.exception.Errors;
import java.util.Arrays;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BlogRequest {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Search {

        @NotNull(message = "검색어는 필수 입니다.")
        private String searchQuery;
        @NotNull(message = "잘못된 정렬 형식입니다.")
        private SearchSort searchSort;
        @Min(1)
        private Integer page = 1;
        @Min(1)
        @Max(100)
        private Integer size = 10;

        public BlogSearchParam toSearchParam() {
            return BlogSearchParam.of(searchQuery,
                    this.searchSort.toBlogSort(),
                    BlogPaging.of(this.page, this.size)
            );
        }
    }

    public enum SearchSort {
        ACCURACY, RECENCY;

        public BlogSort toBlogSort() {
            return Arrays.stream(BlogSort.values())
                    .filter(v -> v.name().equals(this.name()))
                    .findFirst()
                    .orElseThrow(() -> new CustomerException(Errors.BLOG_SEARCH_PARAM_SORT));
        }
    }
}
