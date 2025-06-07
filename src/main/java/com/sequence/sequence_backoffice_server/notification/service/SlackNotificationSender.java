package com.sequence.sequence_backoffice_server.notification.service;

import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class SlackNotificationSender implements NotificationSender {

//    private final SlackService slackService;

    @Override
    public NotificationType getSupportType() {
        return NotificationType.REPORT;
    }

    @Override
    public void send(String title, String content) {
        System.out.println("[슬랙에 전송되었습니다. ]: "+ title + ": " + content);
    }
}
