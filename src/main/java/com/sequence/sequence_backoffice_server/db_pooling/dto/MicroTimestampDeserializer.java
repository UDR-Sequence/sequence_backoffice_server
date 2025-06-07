package com.sequence.sequence_backoffice_server.db_pooling.dto;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken; // JsonToken 임포트
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class MicroTimestampDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 현재 JSON 토큰이 NULL인지 먼저 확인합니다.
        if (p.currentToken() == JsonToken.VALUE_NULL) {
            return null;
        }

        // Long 프리미티브 타입으로 직접 값을 읽습니다.
        // 만약 JSON 값이 유효한 숫자가 아니면 JsonParseException이 발생할 수 있습니다.
        long microSeconds = p.getLongValue();

        // microSeconds는 이제 null이 될 수 없습니다. (프리미티브 타입이므로)

        long epochSecond = microSeconds / 1_000_000L;
        long nanoAdjustment = (microSeconds % 1_000_000L) * 1_000L;

        Instant instant = Instant.ofEpochSecond(epochSecond, nanoAdjustment);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}