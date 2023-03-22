package com.flowerbun.blogsearcher.external.core.factory;

import com.flowerbun.blogsearcher.external.core.BlogRequestConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSearcherConfig {

    private BlogSearcherType type;
    private String apiKey;
    private BlogRequestConverter converter;

    public static BlogSearcherConfig of(BlogSearcherType type, String apiKey) {
        return new BlogSearcherConfig(type, apiKey, null);
    }

    public static BlogSearcherConfig of(BlogSearcherType type, String apiKey, BlogRequestConverter converter) {
        return new BlogSearcherConfig(type, apiKey, converter);
    }

    public boolean hasConverter() {
        return !ObjectUtils.isEmpty(this.converter);
    }
}
