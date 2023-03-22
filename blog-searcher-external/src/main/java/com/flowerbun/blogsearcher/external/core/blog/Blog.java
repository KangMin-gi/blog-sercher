package com.flowerbun.blogsearcher.external.core.blog;

import java.util.List;
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
public class Blog {

    private BlogMeta meta;
    private List<BlogDocument> documents;

}
