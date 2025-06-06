package com.sequence.seuqnece_backoffice_server.db_pooling.dto;

import com.sequence.seuqnece_backoffice_server.db_pooling.dto.DBEventMessage.Payload.After;
import com.sequence.seuqnece_backoffice_server.notification.entity.NotificationMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DBEventMessage implements NotificationMessage {

    private Payload payload;

    @Getter
    @NoArgsConstructor
    public static class Payload {
        private After after;
        private String op;

        @Getter
        @NoArgsConstructor
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
