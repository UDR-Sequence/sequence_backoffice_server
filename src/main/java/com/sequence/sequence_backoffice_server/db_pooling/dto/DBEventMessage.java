package com.sequence.sequence_backoffice_server.db_pooling.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sequence.sequence_backoffice_server.db_pooling.dto.DBEventMessage.Payload.After;
import com.sequence.sequence_backoffice_server.notification.entity.NotificationMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 모르는 필드는 무시하도록 설정
public class DBEventMessage implements NotificationMessage {

    private Payload payload;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        private After after;
        private String op;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class After {
            private Long id;
            private String reportContent;
            private String nickname;
            private String reporter;
            private String reportTarget;
            private String reportType;
            private Long targetId;
            private String createdBy;
            private String modifiedBy;

            // Debezium의 MicroTimestamp (int64)는 기본적으로 Long으로 받습니다.
            // 필요시 Instant나 LocalDateTime으로 변환 로직 추가 필요.
            private Long createdDateTime;
            private Long modifiedDateTime;
            private Long deletedAt;
            private String deletedBy;
            private Integer isDeleted;
        }
    }

    @Override
    public Map<String, String> toPlaceholderMap() {
        Map<String, String> map = new HashMap<>();
        After after = this.payload.getAfter();
        map.put("id", after.getId().toString());
        map.put("reportType", after.getReportType());
        map.put("reportContent", after.getReportContent());
        map.put("reporter", after.getReporter());
        map.put("createdDateTime", after.getCreatedDateTime().toString());
        return map;
    }
}
