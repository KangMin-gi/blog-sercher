package com.flowerbun.blogsearcherapi.querycount.dto;

import com.flowerbun.blogsearcherapi.querycount.BlogSearchQueryCount;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SearchQueryCountDto {

    @Getter
    public static class Results {
        private List<Result> results;

        public Results(List<BlogSearchQueryCount> counts) {
            this.results = counts.stream()
                    .map(Result::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class Result {
        private String query;
        private Integer count;

        public Result(BlogSearchQueryCount count) {
           this.query = count.getSearchQuery();
           this.count = count.getSearchCount();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SearchQueryCounting {
        private String query;

        public BlogSearchQueryCount toEntity() {
            return BlogSearchQueryCount.of(this.query);
        }
    }
}
