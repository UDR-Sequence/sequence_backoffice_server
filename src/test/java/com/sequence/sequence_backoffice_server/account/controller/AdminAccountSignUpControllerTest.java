package com.sequence.sequence_backoffice_server.account.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.sequence_backoffice_server.account.dto.AdminSignupRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.DisplayName;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AdminAccountSignUpControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper; // jackson-databind 필요

        @Test
        @DisplayName("관리자 회원가입이 성공적으로 이루어져야 한다")
        void 관리자회원가입_성공() throws Exception {
                // Given
                AdminSignupRequestDto requestDto = new AdminSignupRequestDto(
                                "admin",
                                "password",
                                "관리자2",
                                "admin@example.com",
                                "ROLE_ADMIN");

                String requestJson = objectMapper.writeValueAsString(requestDto);

                // When
                var resultActions = mockMvc.perform(post("/api/admin/accounts")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson));

                // Then
                resultActions
                                .andExpect(status().isOk())
                                .andDo(print());
        }
}
