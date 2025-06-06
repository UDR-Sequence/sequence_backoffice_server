package com.sequence.sequence_backoffice_server.global.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String message) {
        super(Code.BAD_REQUEST, message);
    }
}
