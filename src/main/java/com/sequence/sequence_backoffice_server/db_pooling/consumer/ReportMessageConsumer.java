package com.sequence.sequence_backoffice_server.db_pooling.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.sequence_backoffice_server.db_pooling.service.ReportHandlerContext;
import com.sequence.sequence_backoffice_server.db_pooling.dto.ReportEventMessage;
import com.sequence.sequence_backoffice_server.global.exception.MessageDeserializerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReportMessageConsumer {

    // private final ObjectMapper objectMapper; // 더 이상 필요 없으므로 제거
    private final ReportHandlerContext reportHandlerContext;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            groupId = "notification-group",
            topics = "dbhistory.sequence.report",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(String rawJson) { // String rawJson 대신 DBEventMessage를 직접 받음
        try {
            ReportEventMessage message = objectMapper.readValue(rawJson, ReportEventMessage.class);
            reportHandlerContext.handle(message);
        }catch (JsonProcessingException e){
            throw new MessageDeserializerException();
        }
        catch (Exception e) { // 그 외의 비즈니스 로직 처리 중 발생할 수 있는 예외
            log.error("메세지 처리 오류 (after deserialization): {}", rawJson, e);
            throw e;// DefaultErrorHandler가 재시도하도록 예외를 던짐
        }
    }
}