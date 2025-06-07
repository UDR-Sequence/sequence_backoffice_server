package com.sequence.sequence_backoffice_server.notification.entity;


import com.sequence.sequence_backoffice_server.global.entity.BaseTimeEntity;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationStatus;
import com.sequence.sequence_backoffice_server.notification.enums.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name="notification")
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="notification_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name="access_id", nullable = false)
    private Long accessId; // 접근할 엔티티의 ID

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="is_read", nullable = false)
    private Boolean isRead;

    @Column(name="receiver_id", nullable = false)
    private Long receiverId; // 알림을 받는 사용자 ID

    @Column(name="status")
    private NotificationStatus status;

    public static Notification from(Long accessId, NotificationType notificationType, String content) {
        return Notification.builder()
                .accessId(accessId)
                .notificationType(notificationType)
                .content(content)
                .isRead(false)
                .build();
    }

    public void updateIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
