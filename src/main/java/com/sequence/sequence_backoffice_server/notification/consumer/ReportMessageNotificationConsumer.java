package com.sequence.sequence_backoffice_server.notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.sequence_backoffice_server.db_pooling.dto.ReportEventMessage;
import com.sequence.sequence_backoffice_server.db_pooling.service.ReportHandlerContext;
import com.sequence.sequence_backoffice_server.global.exception.MessageDeserializerException;
import com.sequence.sequence_backoffice_server.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReportMessageNotificationConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService; // ✅ 알림 처리 전용 서비스 주입

    @KafkaListener(
            groupId = "notification-group",
            topics = "dbhistory.sequence.report",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(String rawJson) {
        try {
            ReportEventMessage message = objectMapper.readValue(rawJson, ReportEventMessage.class);

            notificationService.sendNotification(message);

        } catch (JsonProcessingException e) {
            throw new MessageDeserializerException();
        } catch (Exception e) {
            log.error("알림 처리 오류 (after deserialization): {}", rawJson, e);
            throw e;
        }
    }
}
