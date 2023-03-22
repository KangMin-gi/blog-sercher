package com.flowerbun.blogsearcher.external.core.entity;

import org.springframework.util.MultiValueMap;

public interface BlogSearchRequest {

    String searchQuery();
    MultiValueMap<String, String> queryParam();
    Object bodyParam();
}
