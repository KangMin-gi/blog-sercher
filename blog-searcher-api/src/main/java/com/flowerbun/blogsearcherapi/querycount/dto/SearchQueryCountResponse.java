package com.flowerbun.blogsearcherapi.querycount.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class SearchQueryCountResponse {

    @Getter
    public static class CountResults {
        private List<CountResult> searchQueryCounts = new ArrayList<>();

        public CountResults(SearchQueryCountDto.Results results) {
            this.searchQueryCounts = results.getResults()
                    .stream()
                    .map(CountResult::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class CountResult {
        private Integer count;
        private String searchQuery;

        public CountResult(SearchQueryCountDto.Result result) {
            this.count = result.getCount();
            this.searchQuery = result.getQuery();
        }
    }
}
