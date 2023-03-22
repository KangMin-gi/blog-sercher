package com.flowerbun.blogsearcher.external.core.blog;

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
public class BlogMeta {

    private Integer totalCount;
    private Integer pageableCount;
    private Boolean isEnd;
}
