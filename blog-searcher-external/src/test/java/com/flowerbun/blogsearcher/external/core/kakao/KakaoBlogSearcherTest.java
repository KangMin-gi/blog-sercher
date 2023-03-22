package com.flowerbun.blogsearcher.external.core.kakao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.flowerbun.blogsearcher.external.core.BlogSearcher;
import com.flowerbun.blogsearcher.external.core.blog.Blog;
import com.flowerbun.blogsearcher.external.core.blog.BlogDocument;
import com.flowerbun.blogsearcher.external.core.blog.BlogMeta;
import com.flowerbun.blogsearcher.external.core.blog.BlogPaging;
import com.flowerbun.blogsearcher.external.core.blog.BlogSearchParam;
import com.flowerbun.blogsearcher.external.core.blog.BlogSort;
import com.flowerbun.blogsearcher.external.core.entity.BlogWrapper;
import com.flowerbun.blogsearcher.external.core.entity.Page;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class KakaoBlogSearcherTest {

    BlogSearcher blogSearcher = new KakaoBlogSearcher("552ec45ba7cdd245004d9f9a6088dd4f");

    @Test
    public void okTest() throws Exception {
        Page page = BlogPaging.of(1, 10);
        Integer size = page.sizePerPage();
        Integer pageNumber = page.page();
        BlogWrapper wrapper = this.blogSearcher.search(BlogSearchParam.of(
                "setting.gradle",
                BlogSort.ACCURACY,
                BlogPaging.of(1, 10)
        ));

        // Wrapper 검증
        Blog blog = wrapper.blog();
        assertThat(wrapper)
                .isNotNull();
        assertThat(wrapper.hasError())
                .isFalse();

        // Meta Data 검증
        BlogMeta meta = blog.getMeta();
        assertThat(meta.getIsEnd())
                .isNotNull();
        assertThat(meta.getPageableCount())
                .isNotNull();
        assertThat(meta.getTotalCount())
                .isNotNull();
        assertThat(meta.getTotalCount())
                .isGreaterThan(1);

        //Dcouments 검증
        List<BlogDocument> documents = blog.getDocuments();
        assertThat(documents.size())
                .isLessThanOrEqualTo(size);

        // 내용 검증
        assertThat(documents)
                .allSatisfy(
                        document -> assertThat(document)
                                .hasNoNullFieldsOrProperties()
                )
                .allSatisfy( // 날짜부분, 초기화 안된경우 확인
                        document -> assertThat(document.getDatetime())
                                .isAfter(LocalDate.of(1971, 1, 1).atStartOfDay())
                );

    }

    @Test
    public void Null_PARAMETER_IS_NPE() throws Exception {
        assertThatThrownBy(() -> {
            this.blogSearcher.search(null);
        }).isInstanceOf(NullPointerException.class).hasMessage("blogRequest");

    }
}