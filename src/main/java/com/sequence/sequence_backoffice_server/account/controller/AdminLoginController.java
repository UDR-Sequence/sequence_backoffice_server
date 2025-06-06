package com.sequence.sequence_backoffice_server.account.controller;

import com.sequence.sequence_backoffice_server.account.dto.AdminLoginRequestDto;
import com.sequence.sequence_backoffice_server.account.dto.AdminLoginResponseDto;
import com.sequence.sequence_backoffice_server.account.service.AdminLoginUseCase;
import com.sequence.sequence_backoffice_server.global.dto.ApiResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminLoginUseCase adminLoginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseData<AdminLoginResponseDto>> login(
            @Valid @RequestBody AdminLoginRequestDto requestDto) {
        AdminLoginResponseDto responseDto = adminLoginService.execute(requestDto);
        return ResponseEntity
                .ok()
                .body(ApiResponseData.success(responseDto));
    }
}