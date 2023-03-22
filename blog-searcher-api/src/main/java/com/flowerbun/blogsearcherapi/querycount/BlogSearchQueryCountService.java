package com.flowerbun.blogsearcherapi.querycount;

import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto;
import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto.Results;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogSearchQueryCountService {

    private final BlogSearchQueryCountRepo countRepo;

    public static final int DEFAULT_RESULT_COUNT = 20;

    @Transactional(readOnly = true)
    public SearchQueryCountDto.Results top20SearchQuery() {
        return this.topSearchQuery(DEFAULT_RESULT_COUNT);
    }

    private SearchQueryCountDto.Results topSearchQuery(int resultCount) {
        Pageable top = PageRequest.of(0, resultCount);
        List<BlogSearchQueryCount> queryCounts = this.countRepo.searchQueryCount(top);
        return new Results(queryCounts);
    }
}
