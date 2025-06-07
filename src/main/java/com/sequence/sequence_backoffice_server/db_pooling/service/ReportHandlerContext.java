package com.sequence.sequence_backoffice_server.db_pooling.service;

import com.sequence.sequence_backoffice_server.global.enums.DBOpertation;
import com.sequence.sequence_backoffice_server.db_pooling.dto.ReportEventMessage;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

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
            return;
        }

        reportMessageHandlerStrategy.handle(ReportEventMessage);
    }
}