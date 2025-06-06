package com.sequence.sequence_backoffice_server.notification.service;

import com.sequence.sequence_backoffice_server.global.exception.CannotFindNotificationTemplate;
import com.sequence.sequence_backoffice_server.notification.entity.NotificationMessage;
import com.sequence.sequence_backoffice_server.notification.entity.NotificationTemplate;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import com.sequence.sequence_backoffice_server.notification.repository.NotificationTemplateJpaRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationTemplateUtilService {

    private final NotificationTemplateJpaRepository notificationTemplateJpaRepository;

    public String getNotificationContent(NotificationType notificationType, NotificationMessage message) {
        NotificationTemplate notificationTemplate = notificationTemplateJpaRepository.findByNotificationType(notificationType)
                .orElseThrow(CannotFindNotificationTemplate::new);

        return resolveTemplate(notificationTemplate.getContent(), message.toPlaceholderMap());
    }

    private String resolveTemplate(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return template;
    }
}
