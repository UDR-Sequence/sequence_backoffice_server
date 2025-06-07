package com.sequence.sequence_backoffice_server.notification.service;

import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class NotificationSenderContext {

    private final Map<NotificationType, NotificationSender> senderMap;

    public NotificationSenderContext(List<NotificationSender> senderList) {
        this.senderMap = senderList.stream()
            .collect(Collectors.toMap(NotificationSender::getSupportType, s -> s));
    }

    public void send(NotificationType type, String title, String content) {
        NotificationSender sender = senderMap.get(type);
        if (sender != null) {
            sender.send(title, content);
        }
    }
}
