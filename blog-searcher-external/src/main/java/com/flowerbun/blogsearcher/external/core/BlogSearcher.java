package com.flowerbun.blogsearcher.external.core;

import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.entity.BlogWrapper;

public interface BlogSearcher {

    BlogWrapper search(BlogSearchParam blogSearchParam);
}
