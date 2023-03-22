package com.flowerbun.blogsearcherapi.blog;

import com.flowerbun.blogsearcher.external.core.BlogSearcher;
import com.flowerbun.blogsearcher.external.core.factory.BlogSearcherConfig;
import com.flowerbun.blogsearcher.external.core.factory.BlogSearcherFactory;
import com.flowerbun.blogsearcher.external.core.factory.BlogSearcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BlogBean {

    @Value("${infra.kakao.api-key}")
    private String kakaoApiKey;

    @Bean
    public BlogSearcher kakaoBlogSearcher() {
        BlogSearcherConfig config = BlogSearcherConfig.of(BlogSearcherType.KAKAO, this.kakaoApiKey);
        return BlogSearcherFactory.create(config);
    }
}
