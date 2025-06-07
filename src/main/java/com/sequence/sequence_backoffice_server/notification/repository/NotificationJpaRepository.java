package com.sequence.sequence_backoffice_server.notification.repository;

import com.sequence.sequence_backoffice_server.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {
}
