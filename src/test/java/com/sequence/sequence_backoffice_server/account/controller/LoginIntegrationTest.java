package com.sequence.sequence_backoffice_server.account.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@Transactional
@ActiveProfiles("test")
class LoginIntegrationTest {

    @LocalServerPort
    int port;

    WebClient webClient;

    @BeforeAll
    void setup() {
        webClient = WebClient.builder()
                .baseUrl("http://localhost:" + port)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Test
    @DisplayName("관리자 로그인 시 세션 쿠키가 발급되어야 한다")
    void login_then_access_protected_resource() {
        // given
        Map<String, String> loginRequest = Map.of(
                "username", "superadmin",
                "password", "password");

        // when
        ResponseEntity<Void> response = webClient.post()
                .uri("/api/admin/login")
                .bodyValue(loginRequest)
                .retrieve()
                .toBodilessEntity()
                .block();

        // then
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        String cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        assertThat(cookie).isNotBlank();
        assertThat(cookie).contains("JSESSIONID");
    }
}