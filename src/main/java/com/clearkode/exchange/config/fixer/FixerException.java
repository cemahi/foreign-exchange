package com.clearkode.exchange.config.fixer;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class FixerException extends HttpServerErrorException {

    public FixerException(HttpStatus status, String message) { super(status, message); }
}
