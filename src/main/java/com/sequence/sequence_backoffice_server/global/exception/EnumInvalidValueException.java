package com.sequence.sequence_backoffice_server.global.exception;

public class EnumInvalidValueException extends BaseException {
    public EnumInvalidValueException() {
        super(Code.INVALID_ENUM_VALUE);
    }
}
