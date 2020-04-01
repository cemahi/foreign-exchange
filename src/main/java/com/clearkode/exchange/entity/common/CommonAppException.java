package com.clearkode.exchange.entity.common;

import lombok.Getter;

@Getter
public class CommonAppException extends RuntimeException {

    private final ErrorType error;

    @java.beans.ConstructorProperties({"error"})
    public CommonAppException(ErrorType error) {
        super(String.format("%d %s %s ", error.getCode(), error.getDescription(), error.getHttpStatus()));
        this.error = error;
    }

    @Override
    public String toString() {
        if (error != null)
            return String.format("%d %s %s ", this.error.getCode(), error.getDescription(), error.getHttpStatus());
        return super.getMessage();
    }


}