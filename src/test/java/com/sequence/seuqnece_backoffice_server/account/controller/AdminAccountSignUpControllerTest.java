package com.sequence.seuqnece_backoffice_server.account.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequence.seuqnece_backoffice_server.account.dto.AdminSignupRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class AdminAccountSignUpControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper; // jackson-databind 필요

        @Test
        void 관리자회원가입_성공() throws Exception {
                // Given
                AdminSignupRequestDto requestDto = new AdminSignupRequestDto(
                                "supderadmin",
                                "password",
                                "김대연",
                                "admin@example.com",
                                "ROLE_SUPER_ADMIN");

                String requestJson = objectMapper.writeValueAsString(requestDto);

                // When
                var resultActions = mockMvc.perform(post("/api/admin")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson));

                // Then
                resultActions
                                .andExpect(status().isOk())
                                .andDo(print());
        }
}
