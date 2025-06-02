package com.sequence.seuqnece_backoffice_server.account.controller;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginRequestDto;
import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginResponseDto;
import com.sequence.seuqnece_backoffice_server.account.service.AdminLoginService;
import com.sequence.seuqnece_backoffice_server.global.dto.ApiResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseData<AdminLoginResponseDto>> login(
            @Valid @RequestBody AdminLoginRequestDto requestDto) {
        AdminLoginResponseDto responseDto = adminLoginService.login(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponseData.success(responseDto));
    }
}