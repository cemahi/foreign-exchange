package com.clearkode.exchange.handler.common;

@FunctionalInterface
public interface Handler<T, R> {

    R execute(T request);

}
