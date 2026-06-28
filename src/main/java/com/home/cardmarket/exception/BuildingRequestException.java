package com.home.cardmarket.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BuildingRequestException extends BaseException {

    public BuildingRequestException(String message) {
        super(message);
    }

    public BuildingRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
