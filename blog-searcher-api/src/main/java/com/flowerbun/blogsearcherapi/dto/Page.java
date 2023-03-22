package com.flowerbun.blogsearcherapi.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Page {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Request {
        private Integer page;
        private Integer size;

        public static Request of(int page, int size) {
            return new Request(page, size);
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private Integer totalSize;
        private Integer pageTotalSize;

        public static Response of(int totalSize, int pageTotalSize) {
            return new Response(totalSize, pageTotalSize);
        }
    }
}
