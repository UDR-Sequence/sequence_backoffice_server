package com.sequence.sequence_backoffice_server.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.sequence_backoffice_server.db_pooling.dto.DBEventMessage;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer; // StringDeserializer 임포트
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
// import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer; // 제거
// import org.springframework.kafka.support.serializer.JsonDeserializer; // 제거
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // ObjectMapper는 이제 Kafka Deserializer가 아닌 컨슈머에서 직접 사용됩니다.
    // 다른 곳에서도 사용될 수 있으므로 그대로 둡니다.
    private final ObjectMapper objectMapper;

    public KafkaConsumerConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public ConsumerFactory<String, String> rawJsonConsumerFactory() { // 반환 타입을 <String, String>으로 변경
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 10000);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 40000);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() { // 반환 타입을 <String, String>으로 변경
        ConcurrentKafkaListenerContainerFactory<String, String> factory = // <String, String>
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(rawJsonConsumerFactory()); // rawJsonConsumerFactory 사용

        // 재시도 및 오류 처리 설정
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(new FixedBackOff(1000L, 2L)); // 총 3번 시도
        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}