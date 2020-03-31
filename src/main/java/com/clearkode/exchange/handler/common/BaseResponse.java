package com.clearkode.exchange.handler.common;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseResponse {

    private String status = "SUCCESS";
    private Integer code = 0;
    private String message = "Approved";
    private UUID requestId;

    public BaseResponse() {
        this.status = "SUCCESS";
        this.code = 0;
        this.message = "Approved";
        this.requestId = UUID.randomUUID();
    }

    public BaseResponse(String status, Integer code, String message, UUID requestId) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.requestId = requestId;
    }


}