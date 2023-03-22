package com.flowerbun.blogsearcher.external.core;

import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.entity.BlogSearchRequest;

public interface BlogRequestConverter {

    BlogSearchRequest convert(BlogSearchParam request);

}
