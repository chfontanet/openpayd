package com.openpayd.transactions.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception to manage NOT_FOUND responses
 */
@ResponseStatus(value = NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }
}
