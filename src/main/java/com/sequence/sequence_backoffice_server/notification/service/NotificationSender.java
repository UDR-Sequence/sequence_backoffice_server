package com.sequence.sequence_backoffice_server.notification.service;

import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;

public interface NotificationSender {
    NotificationType getSupportType();
    void send(String title, String content);
}
