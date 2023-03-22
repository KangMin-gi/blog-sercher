package com.flowerbun.blogsearcherapi.querycount;

import static org.assertj.core.api.Assertions.assertThat;

import com.flowerbun.blogsearcherapi.querycount.dto.SearchQueryCountDto.SearchQueryCounting;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class BlogSearchQueryCalculateServiceTest {

    @Autowired
    BlogSearchQueryCalculateService calculateService;

    @Autowired
    BlogSearchQueryCountService countService;

    @Test
    @Transactional
    public void calculateQueryCount() throws Exception {
        String searchQuery = "안녕하세요와EnglishHello와";
        int iterCount = 100;
        SearchQueryCounting counting = new SearchQueryCounting(searchQuery);
        for (int i = 0; i < 100; ++i) {
            this.calculateService.calculateQueryCount(counting);
        }

        assertThat(this.countService.top20SearchQuery()
                .getResults()
                .stream()
                .filter(v -> searchQuery.equals(v.getQuery()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("NOSEARCHQUERY"))
        ).isNotNull()
                .isNotInstanceOf(NoSuchElementException.class)
                .matches(v -> v.getCount().equals(100));
    }

    @Test
    @Transactional
    public void calculateQueryCountPare() throws Exception {
        String searchQuery = "안녕하세요와EnglishHello와";
        int threadNumber = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumber);
        CountDownLatch latch = new CountDownLatch(threadNumber);
        SearchQueryCounting counting = new SearchQueryCounting(searchQuery);

        for (int i = 0; i < threadNumber; ++i) {
            executorService.execute(() -> {
                this.calculateService.calculateQueryCount(counting);
                latch.countDown();
            });

        }
        latch.await();

        assertThat(this.countService.top20SearchQuery()
                .getResults()
                .stream()
                .filter(v -> searchQuery.equals(v.getQuery()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("NOSEARCHQUERY"))
        ).isNotNull()
                .isNotInstanceOf(NoSuchElementException.class)
                .matches(v -> v.getCount().equals(threadNumber))
        ;
    }
}