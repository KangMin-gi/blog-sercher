package com.flowerbun.blogsearcher.external.core.blog;

import com.flowerbun.blogsearcher.external.core.entity.Page;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPaging implements Page {
    private Integer page;
    private Integer size;

    public static BlogPaging of(Integer page, Integer size) {
        Objects.requireNonNull(page);
        Objects.requireNonNull(size);

        return new BlogPaging(page, size);
    }

    @Override
    public Integer page() {
        return this.page;
    }

    @Override
    public Integer sizePerPage() {
        return this.size;
    }
}
