package com.sequence.seuqnece_backoffice_server.account.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class LoginIntegrationTest {

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
    void login_then_access_protected_resource() {
        // 1. 로그인 요청
        Mono<ClientResponse> loginResponseMono = webClient.post()
                .uri("/api/admin/login")
                .bodyValue(Map.of(
                        "username", "superadmin",
                        "password", "password"
                ))
                .exchange();

        ClientResponse loginResponse = loginResponseMono.block();
        String cookie = loginResponse.cookies().getFirst("JSESSIONID").getValue();
        assertThat(cookie).isNotBlank();
        assertThat(loginResponse.statusCode().is2xxSuccessful()).isTrue();
    }
}