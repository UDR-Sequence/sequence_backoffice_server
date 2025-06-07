package com.sequence.sequence_backoffice_server.notification.util;

import com.sequence.sequence_backoffice_server.notification.entity.NotificationTemplate;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import com.sequence.sequence_backoffice_server.notification.repository.NotificationTemplateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationTemplateInitializer implements ApplicationRunner {

    private final NotificationTemplateJpaRepository notificationTemplateJpaRepository;

    @Override
    public void run(ApplicationArguments args) {
        NotificationType type = NotificationType.REPORT;
        boolean exists = notificationTemplateJpaRepository.existsByNotificationType(type);

        if (!exists) {
            NotificationTemplate template = NotificationTemplate.builder()
                .notificationType(type)
                .title("[신고 접수] 새로운 신고가 등록되었습니다.")
                .content("""
                    신고 ID: {{id}}
                    신고 유형: {{reportType}}
                    신고자: {{reporter}}
                    신고 내용: {{reportContent}}
                    신고 시간: {{createdDateTime}}
                    """)
                .build();

            notificationTemplateJpaRepository.save(template);
        }
    }
}
