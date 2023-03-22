package com.flowerbun.blogsearcher.external.core.factory;

import com.flowerbun.blogsearcher.external.core.BlogSearcher;
import com.flowerbun.blogsearcher.external.core.kakao.KakaoBlogSearcher;

public class BlogSearcherFactory {

    public static BlogSearcher create(BlogSearcherConfig config) {
        if (config.getType() == BlogSearcherType.KAKAO) {
            return config.hasConverter()
                    ? new KakaoBlogSearcher(config.getApiKey(), config.getConverter())
                    : new KakaoBlogSearcher(config.getApiKey());
        }
        throw new IllegalArgumentException("[BlogSearcherFactory Type Error] " + config.getType().name());
    }
}
