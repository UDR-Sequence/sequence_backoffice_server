package com.sequence.sequence_backoffice_server.global.exception;

public class UserNotFindException extends BaseException {
    public UserNotFindException() {
        super(Code.USER_NOT_FOUND);
    }
}
