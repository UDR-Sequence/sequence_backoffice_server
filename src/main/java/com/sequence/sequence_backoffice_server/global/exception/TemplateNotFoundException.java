package com.sequence.sequence_backoffice_server.global.exception;

import com.sequence.sequence_backoffice_server.global.entity.BaseTimeEntity;

public class TemplateNotFoundException extends BaseException {
    public TemplateNotFoundException() {
        super(Code.CANNOT_FIND_NOTIFICATION_TEMPLATE);
    }
}
