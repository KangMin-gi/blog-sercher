package com.flowerbun.blogsearcherapi.querycount;

import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto.Results;
import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountResponse.CountResults;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("query-count")
public class SearchQueryCountController {

    private final BlogSearchQueryCountService queryCountService;

    @GetMapping("/v1")
    public ResponseEntity<CountResults> countResults() {
        Results results = this.queryCountService.top20SearchQuery();
        CountResults countResults = new CountResults(results);
        return ResponseEntity.ok(countResults);
    }
}
