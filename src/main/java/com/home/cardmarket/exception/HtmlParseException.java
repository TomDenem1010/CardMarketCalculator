package com.home.cardmarket.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HtmlParseException extends BaseException {

    public HtmlParseException(String message) {
        super(message);
    }

    public HtmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
