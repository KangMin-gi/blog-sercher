package com.flowerbun.blogsearcher.external.core.kakao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.flowerbun.blogsearcher.external.core.entity.Page;
import org.junit.jupiter.api.Test;

class KakaoBlogSearchParamTest {

    KakaoBlogPage correctPage = KakaoBlogPage.of(1, 1);


    @Test
    public void okTest() {
        assertThat(KakaoBlogRequest.of("user", KakaoBlogSort.RECENCY, correctPage))
                .isNotNull();
    }

    @Test
    public void of의_파라미터가_null이면_NPE() {

        assertThatThrownBy(() -> KakaoBlogRequest.of(null, KakaoBlogSort.RECENCY, correctPage))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("query");

        assertThatThrownBy(() -> KakaoBlogRequest.of("query", null, correctPage))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("sort");

        assertThatThrownBy(() -> KakaoBlogRequest.of("query2", KakaoBlogSort.RECENCY, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("page");

    }

    @Test
    public void page의_값중일부가_null이면_npe() {



        assertThatThrownBy(() -> KakaoBlogRequest.of("QUERY", KakaoBlogSort.RECENCY, KakaoBlogPage.wrap(nullPage)))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("page");

        assertThatThrownBy(() -> KakaoBlogRequest.of("QUERY", KakaoBlogSort.RECENCY, KakaoBlogPage.wrap(nullSize)))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("size");

    }

    Page nullPage = new Page() {
        @Override
        public Integer page() {
            return null;
        }

        @Override
        public Integer sizePerPage() {
            return 1;
        }
    };
    Page nullSize = new Page() {
        @Override
        public Integer page() {
            return 1;
        }

        @Override
        public Integer sizePerPage() {
            return null;
        }
    };

}