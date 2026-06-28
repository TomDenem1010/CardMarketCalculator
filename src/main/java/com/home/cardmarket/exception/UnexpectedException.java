package com.home.cardmarket.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnexpectedException extends BaseException {

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

}
