package com.flowerbun.blogsearcher.external.core.entity;

import com.flowerbun.blogsearcher.external.core.blog.Blog;

public interface BlogWrapper {

    Blog blog() throws NoBlogContentException;
    boolean hasError();
    String errorMessage();
}
