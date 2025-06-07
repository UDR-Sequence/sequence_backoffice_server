package com.sequence.sequence_backoffice_server.notification.repository;

import com.sequence.sequence_backoffice_server.notification.entity.NotificationTemplate;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTemplateJpaRepository extends JpaRepository<NotificationTemplate, Long> {
    Optional<NotificationTemplate> findByNotificationType(NotificationType notificationType);
    boolean existsByNotificationType(NotificationType type);
}
