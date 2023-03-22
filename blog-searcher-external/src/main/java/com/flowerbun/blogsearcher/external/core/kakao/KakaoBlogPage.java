package com.flowerbun.blogsearcher.external.core.kakao;

import com.flowerbun.blogsearcher.external.core.blog.BlogPaging;
import com.flowerbun.blogsearcher.external.core.entity.Page;
import java.util.Objects;

public class KakaoBlogPage implements Page {

    private static final Integer PAGE_DEFAULT = 1;
    private static final Integer SIZE_DEFAULT = 10;

    private Page page;

    public static KakaoBlogPage of(Integer page, Integer size) {
        return new KakaoBlogPage(BlogPaging.of(page, size));
    }
    public static KakaoBlogPage wrap(Page page) {
        return new KakaoBlogPage(page);
    }

    public static KakaoBlogPage ofDefault() {
        return of(PAGE_DEFAULT, SIZE_DEFAULT);
    }


    KakaoBlogPage(Page page) {
        this.validPage(page.page());
        this.validSize(page.sizePerPage());
        this.page = page;
    }

    private void validPage(Integer page) {
        Objects.requireNonNull(page, "page");
        if (page <= 0) {
            throw new IllegalArgumentException("페이지는 0보다 작거나 같을 수 없습니다.");
        }
        if (page > 50) {
            throw new IllegalArgumentException("검색 페이지는 50 페이지를 넘길 수 없습니다.");
        }
    }

    private void validSize(Integer size) {
        Objects.requireNonNull(size, "size");
        if (size <= 0) {
            throw new IllegalArgumentException("페이지별 size는 0보다 작을 수 없습니다.");
        }
        if (size > 50) {
            throw new IllegalArgumentException("페이지별 size는 50개를 넘길 수 없습니다.");
        }

    }

    @Override
    public Integer page() {
        return this.page.page();
    }

    @Override
    public Integer sizePerPage() {
        return this.page.sizePerPage();
    }
}
