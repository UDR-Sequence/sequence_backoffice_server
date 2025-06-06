package com.sequence.seuqnece_backoffice_server.db_pooling.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.seuqnece_backoffice_server.db_pooling.service.ReportHandlerContext;
import com.sequence.seuqnece_backoffice_server.global.exception.MessageDeserializerException;
import com.sequence.seuqnece_backoffice_server.db_pooling.dto.DBEventMessage;
import com.sequence.seuqnece_backoffice_server.db_pooling.dto.DBEventMessage.Payload.After;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReportMessageConsumer {

    private final ObjectMapper objectMapper;
    private final ReportHandlerContext reportHandlerContext;

    @KafkaListener(
        groupId = "notification-group",
        topics = "dbhistory.sequence.report",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(String rawJson) {
        try {
            DBEventMessage message = objectMapper.readValue(rawJson, DBEventMessage.class);
            String op = message.getPayload().getOp();
            reportHandlerContext.handle(op, message);

        }catch (JsonProcessingException e) {
            throw new MessageDeserializerException();
        }
    }
}
