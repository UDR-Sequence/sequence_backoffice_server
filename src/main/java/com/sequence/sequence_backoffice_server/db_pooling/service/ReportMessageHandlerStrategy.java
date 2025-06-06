package com.sequence.sequence_backoffice_server.db_pooling.service;

import com.sequence.sequence_backoffice_server.global.enums.DBOpertation;
import com.sequence.sequence_backoffice_server.db_pooling.dto.DBEventMessage;

public interface ReportMessageHandlerStrategy {
    DBOpertation getSupportDBOpertation();  // 핸들러가 처리할 op 유형
    void handle(DBEventMessage message);  // 실제 처리 로직
}
