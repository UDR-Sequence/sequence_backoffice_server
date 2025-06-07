package com.sequence.sequence_backoffice_server.db_pooling.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sequence.sequence_backoffice_server.notification.entity.NotificationMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportEventMessage implements NotificationMessage {

    private CommonPayload<After> payload;

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
        private Long createdDateTime;
        private Long modifiedDateTime;
        private Long deletedAt;
        private String deletedBy;
        private Integer isDeleted;
    }

    @Override
    public Map<String, String> toPlaceholderMap() {
        Map<String, String> map = new HashMap<>();
        After after = this.getPayload().getAfter();
        map.put("id", String.valueOf(after.getId()));
        map.put("reportType", after.getReportType());
        map.put("reportContent", after.getReportContent());
        map.put("reporter", after.getReporter());
        map.put("createdDateTime", String.valueOf(after.getCreatedDateTime()));
        return map;
    }
}