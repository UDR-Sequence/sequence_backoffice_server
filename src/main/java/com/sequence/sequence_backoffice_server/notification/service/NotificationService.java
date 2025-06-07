package com.sequence.sequence_backoffice_server.notification.service;

import com.sequence.sequence_backoffice_server.db_pooling.dto.ReportEventMessage;
import com.sequence.sequence_backoffice_server.global.exception.TemplateNotFoundException;
import com.sequence.sequence_backoffice_server.notification.entity.Notification;
import com.sequence.sequence_backoffice_server.notification.entity.NotificationTemplate;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import com.sequence.sequence_backoffice_server.notification.repository.NotificationJpaRepository;
import com.sequence.sequence_backoffice_server.notification.repository.NotificationTemplateJpaRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationTemplateJpaRepository templateRepository;
    private final NotificationMessageFactory messageFactory;
    private final NotificationSenderContext senderContext;
    private final NotificationJpaRepository notificationRepository;

    @Transactional
    public void sendNotification(ReportEventMessage event) {
        // 1. 템플릿 조회
        NotificationTemplate template = templateRepository.findByNotificationType(NotificationType.REPORT)
                .orElseThrow(TemplateNotFoundException::new);

        // 2. 템플릿 치환
        Map<String, String> placeholders = event.toPlaceholderMap();
        String content = messageFactory.render(template.getContent(), placeholders);
        String title = template.getTitle();

        // 3. DB 저장
        Notification notification = Notification.from(event.getPayload().getAfter().getTargetId(), NotificationType.REPORT, content);
        notificationRepository.save(notification);

        // 4. 전송 (전략에 따라 Slack 등)
        senderContext.send(NotificationType.REPORT, title, content);
    }
}
