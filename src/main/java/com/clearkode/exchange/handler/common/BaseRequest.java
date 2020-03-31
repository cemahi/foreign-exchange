package com.clearkode.exchange.handler.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class BaseRequest {
    @JsonIgnore
    private UUID requestId = UUID.randomUUID();
}
