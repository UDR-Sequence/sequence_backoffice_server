package com.sequence.sequence_backoffice_server.global.exception;


public class AuthException extends BaseException {
    public AuthException(String message) {
        super(Code.ACCESS_DENIED,message);
    }
}
