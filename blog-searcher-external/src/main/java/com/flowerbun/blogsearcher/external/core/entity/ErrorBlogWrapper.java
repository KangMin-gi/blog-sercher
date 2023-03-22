package com.flowerbun.blogsearcher.external.core.entity;

import com.flowerbun.blogsearcher.external.core.blog.Blog;

public class ErrorBlogWrapper implements BlogWrapper {

    private String errorMessage;

    public ErrorBlogWrapper(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Blog blog() throws NoBlogContentException {
        throw new NoBlogContentException();
    }

    @Override
    public boolean hasError() {
        return true;
    }

    @Override
    public String errorMessage() {
        return errorMessage;
    }
}
