package com.home.cardmarket.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SendingRequestException extends BaseException {

    public SendingRequestException(String message) {
        super(message);
    }

    public SendingRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
