package com.flowerbun.blogsearcherapi.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;

    public ErrorResponse(IllegalArgumentException iae) {
        this.code = "IAE";
        this.message = iae.getMessage();
    }

    public ErrorResponse(CustomerException customerException) {
        this.code = customerException.code();
        this.message = customerException.getMessage();
    }

}
