package com.openpayd.transactions.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Exception to manage BAD_REQUEST responses
 */
@ResponseStatus(value = BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(final String message) {
        super(message);
    }
}
