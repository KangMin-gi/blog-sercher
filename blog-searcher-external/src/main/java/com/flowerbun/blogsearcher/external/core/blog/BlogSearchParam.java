package com.flowerbun.blogsearcher.external.core.blog;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSearchParam {

    private String query;
    private BlogSort blogSort;
    private BlogPaging blogPaging;

    public static BlogSearchParam of(String query, BlogSort sort, BlogPaging paging) {
        Objects.requireNonNull(query);
        Objects.requireNonNull(sort);
        Objects.requireNonNull(paging);
        return new BlogSearchParam(query, sort, paging);
    }
}
