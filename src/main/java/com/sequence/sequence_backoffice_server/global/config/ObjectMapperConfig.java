package com.sequence.sequence_backoffice_server.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; // LocalDateTime 등을 사용한다면 필요
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // JSON의 snake_case (예: created_date_time)를 Java의 camelCase (예: createdDateTime)로 자동 매핑
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // LocalDateTime, Instant 등 Java 8 날짜/시간 API 사용을 위해 필수
        objectMapper.registerModule(new JavaTimeModule());
        // DTO에 없는 필드가 JSON에 있을 경우 무시 (유연성 확보)
        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}