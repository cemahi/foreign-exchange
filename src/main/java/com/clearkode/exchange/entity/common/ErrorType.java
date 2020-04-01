package com.clearkode.exchange.entity.common;

import org.springframework.http.HttpStatus;


public enum ErrorType {
    ENTITY_NOT_FOUND(101, "Entity not found", HttpStatus.INTERNAL_SERVER_ERROR),
    AT_LEAST_ONE_ELEMENT_HAS_TO_SELECTED(102, "At least one element has to selected", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorType(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}