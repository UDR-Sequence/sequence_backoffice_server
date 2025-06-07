package com.sequence.sequence_backoffice_server.notification.service;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class NotificationMessageFactory {
    public String render(String template, Map<String, String> values) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            template = template.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return template;
    }
}
