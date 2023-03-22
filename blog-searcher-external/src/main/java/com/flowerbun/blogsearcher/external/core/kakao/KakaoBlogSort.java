package com.flowerbun.blogsearcher.external.core.kakao;


import com.flowerbun.blogsearcher.external.core.blog.BlogSort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KakaoBlogSort {

    ACCURACY("accuracy", "정확도순", BlogSort.ACCURACY),
    RECENCY("recency", "최신순", BlogSort.RECENCY);

    private String command;
    private String description;
    private BlogSort blogSort;

    String requestCommand() {
        return this.command;
    }
    boolean isEqualBlogSort(BlogSort blogSort) {
        return this.blogSort == blogSort;
    }
}
