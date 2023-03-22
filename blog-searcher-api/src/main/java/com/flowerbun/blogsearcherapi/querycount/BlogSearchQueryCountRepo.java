package com.flowerbun.blogsearcherapi.querycount;

import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface BlogSearchQueryCountRepo extends JpaRepository<BlogSearchQueryCount, Long> {

    @Query(""
            + " SELECT bsqc "
            + "   FROM BlogSearchQueryCount bsqc "
            + "  ORDER BY bsqc.searchCount DESC ")
    List<BlogSearchQueryCount> searchQueryCount(Pageable pageable);

    @Query(""
            + " SELECT bsqc "
            + "   FROM BlogSearchQueryCount bsqc "
            + "  WHERE bsqc.searchQuery = :searchQuery ")
    @Lock(LockModeType.PESSIMISTIC_READ)
    BlogSearchQueryCount findBySearchQueryWithLock(String searchQuery);
}
