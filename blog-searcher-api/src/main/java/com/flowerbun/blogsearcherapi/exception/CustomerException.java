package com.flowerbun.blogsearcherapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomerException extends RuntimeException {

    private Errors errors;

    public CustomerException(Errors errors) {
        super(errors.getMessage());
        this.errors = errors;
    }

    public String code() {
        return this.errors.getCustomCode();
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(this.errors.getStatusCode());
    }


}
