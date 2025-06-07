package com.sequence.sequence_backoffice_server.notification.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    REPORT("신고 접수"),
    ;

    private final String description;
}
