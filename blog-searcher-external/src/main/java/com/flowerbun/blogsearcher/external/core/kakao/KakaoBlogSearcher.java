package com.flowerbun.blogsearcher.external.core.kakao;

import com.flowerbun.blogsearcher.external.core.BlogRequestConverter;
import com.flowerbun.blogsearcher.external.core.BlogSearcher;
import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.entity.BlogSearchRequest;
import com.flowerbun.blogsearcher.external.core.entity.BlogWrapper;
import com.flowerbun.blogsearcher.external.core.entity.ErrorBlogWrapper;
import com.flowerbun.blogsearcher.external.core.rest.RestTemplateFactory;
import java.net.URI;
import java.util.Objects;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class KakaoBlogSearcher implements BlogSearcher {

    private static final BlogRequestConverter DEFAULT_CONVERTER = new KakaoRequestConvert();

    private final String apiKey;
    private final BlogRequestConverter converter;

    public KakaoBlogSearcher(String apiKey, BlogRequestConverter converter) {
        this.apiKey = apiKey;
        this.converter = converter;
    }

    public KakaoBlogSearcher(String apiKey) {
        this(apiKey, DEFAULT_CONVERTER);
    }

    @Override
//    public BlogWrapper search(BlogSearchRequest searchParam) {
    public BlogWrapper search(BlogSearchParam blogSearchParam) {
        Objects.requireNonNull(blogSearchParam, "blogRequest");
        BlogSearchRequest searchParam = this.converter.convert(blogSearchParam);


        RestTemplate restTemplate = RestTemplateFactory.restTemplate();
        ResponseEntity<KakaoBlog> exchange = null;
        try {
            exchange = restTemplate.exchange(
                    this.uri(searchParam),
                    HttpMethod.GET,
                    this.entity(),
                    KakaoBlog.class
            );
        } catch (HttpServerErrorException serverException) {
            serverException.printStackTrace();
        } catch (HttpClientErrorException clientException) {
            clientException.printStackTrace();
        } catch (Exception other) {
            other.printStackTrace();
        }

        if (ObjectUtils.isEmpty(exchange)) {
            return new ErrorBlogWrapper("KakaoBlog Loading Fail");
        }

        return exchange.getBody();
    }

    private URI uri(BlogSearchRequest request) {
        return UriComponentsBuilder
                .fromHttpUrl("http://dapi.kakao.com/v2/search/blog")
                .queryParams(request.queryParam())
                .build()
                .toUri();
    }

    public HttpEntity<Void> entity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        return entity;
    }

}
