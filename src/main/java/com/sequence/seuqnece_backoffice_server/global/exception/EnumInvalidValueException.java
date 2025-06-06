package com.sequence.seuqnece_backoffice_server.global.exception;

public class EnumInvalidValueException extends BaseException {
    public EnumInvalidValueException() {
        super(Code.INVALID_ENUM_VALUE);
    }
}
