package com.flowerbun.blogsearcher.external.core.kakao;

import com.flowerbun.blogsearcher.external.core.BlogRequestConverter;
import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.entity.BlogSearchRequest;
import java.util.Arrays;

public class KakaoRequestConvert implements BlogRequestConverter {

    @Override
    public BlogSearchRequest convert(BlogSearchParam request) {
        KakaoBlogSort kakaoBlogSort = Arrays.stream(KakaoBlogSort.values())
                .filter(v -> v.isEqualBlogSort(request.getBlogSort()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Kakao에서 사용할 수 없는 BlogSort 형식입니다."));

        return KakaoBlogRequest.of(
                request.getQuery(),
                kakaoBlogSort,
                KakaoBlogPage.wrap(request.getBlogPaging())
        );
    }
}
