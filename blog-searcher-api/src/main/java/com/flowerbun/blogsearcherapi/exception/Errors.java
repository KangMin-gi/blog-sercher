package com.flowerbun.blogsearcherapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Errors {

    BLOG_SEARCH("BSE_1", 500, "블로그 검색에 오류가 있습니다. 다시 시도해주세요"),
    BLOG_SEARCH_PARAM_SORT("BSPS_1", 400, "블로그 정렬 방식 에러입니다. 지원하지 않는 형식"),
    UNKNOWN_SERVER_ERROR("USE_1", 500, "알수 없는 서버 오류입니다.");


    private String customCode;
    private int statusCode;
    private String message;
}
