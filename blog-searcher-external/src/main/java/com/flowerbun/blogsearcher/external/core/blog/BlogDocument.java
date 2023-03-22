package com.flowerbun.blogsearcher.external.core.blog;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BlogDocument {

    private LocalDateTime datetime;
    private String contents;
    private String title;
    private String blogName;
    private String thumbnail;
    private String url;
}
