package com.sequence.sequence_backoffice_server.global.exception;

public class MessageDeserializerException extends BaseException {
    public MessageDeserializerException() {
        super(Code.MESSAGE_DESERIALIZER_ERROR);
    }

}
