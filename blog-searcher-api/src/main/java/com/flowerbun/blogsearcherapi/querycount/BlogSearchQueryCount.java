package com.flowerbun.blogsearcherapi.querycount;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Table(name = "BlogSearchQueryCount")
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchQueryCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "searchQuery")
    private String searchQuery;

    @Column(name = "searchCount")
    private Integer searchCount = 1;

    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate = LocalDateTime.now();

    public static BlogSearchQueryCount of(String searchQuery) {
        BlogSearchQueryCount blogSearchQueryCount = new BlogSearchQueryCount();
        blogSearchQueryCount.searchQuery = searchQuery;
        return blogSearchQueryCount;
    }

    public void addCount() {
        this.searchCount += 1;
    }

    @PreUpdate
    @PrePersist
    protected void syncLastUpdatedDate() {
        this.lastUpdateDate = LocalDateTime.now();
    }
}
