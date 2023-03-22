package com.flowerbun.blogsearcher.external.core.entity;

public interface Page {

    /**
     * 검색할 페이지
     * @return
     */
    Integer page();

    /**
     * Page 별 사이즈 (한 페이지에 보여질 수)
     * @return
     */
    Integer sizePerPage();

}
