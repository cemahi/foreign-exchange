package com.clearkode.exchange.ratesapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class RatesApiException extends HttpServerErrorException {

    public RatesApiException(HttpStatus status, String message) { super(status, message); }
}
