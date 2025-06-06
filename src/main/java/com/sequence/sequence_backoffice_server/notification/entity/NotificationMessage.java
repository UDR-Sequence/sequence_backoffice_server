package com.sequence.sequence_backoffice_server.notification.entity;

import java.util.Map;

public interface NotificationMessage {

    Map<String, String> toPlaceholderMap();
}
