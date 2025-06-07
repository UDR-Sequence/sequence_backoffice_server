package com.sequence.sequence_backoffice_server.db_pooling.service;

import com.sequence.sequence_backoffice_server.global.enums.DBOpertation;
import com.sequence.sequence_backoffice_server.db_pooling.dto.ReportEventMessage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReportHandlerContext {

    private final Map<DBOpertation, ReportMessageHandlerStrategy> reportStrategyMap;

    public ReportHandlerContext(List<ReportMessageHandlerStrategy> reportMessageHandlerStrategieList) {
        this.reportStrategyMap = reportMessageHandlerStrategieList.stream()
                .collect(Collectors.toMap(ReportMessageHandlerStrategy::getSupportDBOpertation, strategy -> strategy));
    }

    public void handle(ReportEventMessage ReportEventMessage) {
        String op = ReportEventMessage.getPayload().getOp();
        ReportMessageHandlerStrategy reportMessageHandlerStrategy = reportStrategyMap.get(DBOpertation.from(op));
        if (reportMessageHandlerStrategy == null) {
            log.warn("지원하지 않는 DBOpertation: {}", op);
            return; // 이건 사실 괜찮지만, Kafka 쪽 예외로 인식될 수도 있음
        }

        reportMessageHandlerStrategy.handle(ReportEventMessage);
    }
}