package com.flowerbun.blogsearcher.external.core.kakao;

import com.flowerbun.blogsearcher.external.core.entity.BlogSearchRequest;
import java.util.Objects;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
public class KakaoBlogRequest implements BlogSearchRequest {

    private String query;
    private String sort;
    private Integer page;
    private Integer size;

    public static KakaoBlogRequest of(final String query,
                                      final KakaoBlogSort sort,
                                      final KakaoBlogPage page) {
        Objects.requireNonNull(sort, "sort");
        Objects.requireNonNull(page, "page");
        return new KakaoBlogRequest(query, sort.requestCommand(), page.page(), page.sizePerPage());
    }

    private KakaoBlogRequest() {
    }

    private KakaoBlogRequest(String query, String sort, Integer page, Integer size) {
        Objects.requireNonNull(query, "query");
        Objects.requireNonNull(sort, "sort");
        Objects.requireNonNull(page, "page");
        Objects.requireNonNull(size, "size");

        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    @Override
    public String searchQuery() {
        return query;
    }

    @Override
    public MultiValueMap<String, String> queryParam() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("query", this.query);
        map.add("sort", this.sort);
        map.add("page", String.valueOf(this.page));
        map.add("size", String.valueOf(this.size));
        return map;
    }

    @Override
    public Object bodyParam() {
        throw new IllegalArgumentException("[KakaoBlogRequest] Can Not Use bodyParam()");
    }
}
