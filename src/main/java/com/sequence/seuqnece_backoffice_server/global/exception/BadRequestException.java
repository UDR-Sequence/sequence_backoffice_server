package com.sequence.seuqnece_backoffice_server.global.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(Code.BAD_REQUEST, message);
    }
}
