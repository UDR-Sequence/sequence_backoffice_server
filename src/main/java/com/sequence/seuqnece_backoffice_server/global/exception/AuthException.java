package com.sequence.seuqnece_backoffice_server.global.exception;


public class AuthException extends BaseException {
    public AuthException(String message) {
        super(Code.ACCESS_DENIED,message);
    }
}
