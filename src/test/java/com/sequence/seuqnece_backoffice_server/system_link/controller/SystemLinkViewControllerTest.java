package com.sequence.seuqnece_backoffice_server.system_link.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class SystemLinkViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("시스템 링크 목록을 성공적으로 조회해야 한다")
    @WithMockUser(roles = "SUPER_ADMIN")
    void 시스템링크_목록_조회_성공() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/admin/system-links")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].name").exists())
                .andExpect(jsonPath("$.data[0].url").exists())
                .andExpect(jsonPath("$.data[0].description").exists())
                .andDo(print())
                .andReturn();

    }
}