package com.flowerbun.blogsearcherapi.querycount;

import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto.SearchQueryCounting;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogSearchQueryCalculateService {

    private final BlogSearchQueryCountRepo countRepo;
    private final Set<String> querySet = new ConcurrentSkipListSet<>();


    @Transactional
    public void calculateQueryCount(SearchQueryCounting searchQueryCounting) {
        String query = searchQueryCounting.getQuery();
        if (querySet.contains(query)) {
            BlogSearchQueryCount existQueryCount = this.countRepo
                    .findBySearchQueryWithLock(searchQueryCounting.getQuery());
            existQueryCount.addCount();
            return;
        }

        this.countRepo.save(searchQueryCounting.toEntity()); //
        querySet.add(query);
    }
}
