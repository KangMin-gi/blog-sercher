package com.flowerbun.blogsearcher.external.core.entity;

public class NoBlogContentException extends Exception {

    public NoBlogContentException() {
        super("검색 오류로 Blog를 불러오지 못했습니다.");
    }

    public NoBlogContentException(String errorMessage) {
        super(errorMessage);
    }
}
