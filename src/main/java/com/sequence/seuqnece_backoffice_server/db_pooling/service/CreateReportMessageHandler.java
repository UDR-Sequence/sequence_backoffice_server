package com.sequence.seuqnece_backoffice_server.db_pooling.service;

import com.sequence.seuqnece_backoffice_server.global.enums.DBOpertation;
import com.sequence.seuqnece_backoffice_server.db_pooling.dto.DBEventMessage;

public class CreateReportMessageHandler implements ReportMessageHandlerStrategy{
    @Override
    public DBOpertation getSupportDBOpertation() {
        return DBOpertation.CREATE;
    }

    @Override
    public void handle(DBEventMessage message) {
        System.out.println(message.getPayload().getAfter().getReportContent());
    }
}
