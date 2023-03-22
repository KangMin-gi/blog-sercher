package com.flowerbun.blogsearcher.external.core.kakao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.flowerbun.blogsearcher.external.core.blog.Blog;
import com.flowerbun.blogsearcher.external.core.blog.BlogDocument;
import com.flowerbun.blogsearcher.external.core.blog.BlogMeta;
import com.flowerbun.blogsearcher.external.core.entity.BlogWrapper;
import com.flowerbun.blogsearcher.external.core.entity.NoBlogContentException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoBlog implements BlogWrapper {

    private KakaoBlogMeta meta;
    private List<KakaoBlogDocument> documents;

    @Override
    public Blog blog() throws NoBlogContentException {
        BlogMeta blogMeta = new BlogMeta(meta.totalCount, meta.pageableCount, meta.isEnd);
        List<BlogDocument> documents = this.documents.stream()
                .map(KakaoBlogDocument::toBlogDocument)
                .collect(Collectors.toList());
        return new Blog(blogMeta, documents);
    }

    @Override
    public boolean hasError() {
        return false;
    }

    @Override
    public String errorMessage() {
        return null;
    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoBlogMeta {

        private Integer totalCount;
        private Integer pageableCount;
        private Boolean isEnd;

    }

    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class KakaoBlogDocument {

        private String title;
        private String contents;
        private String url;

        @JsonProperty("blogname")
        private String blogName;
        private String thumbnail;

        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone="Asia/Seoul")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime datetime;

        private BlogDocument toBlogDocument() {
            return new BlogDocument(
                    this.datetime,
                    this.contents,
                    this.title,
                    this.blogName,
                    this.thumbnail,
                    this.url);
        }
    }
}
