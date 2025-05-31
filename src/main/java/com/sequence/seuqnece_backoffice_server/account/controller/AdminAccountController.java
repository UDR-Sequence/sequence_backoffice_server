package com.sequence.seuqnece_backoffice_server.account.controller;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminSignupRequestDto;
import com.sequence.seuqnece_backoffice_server.account.service.AdminSignupService;
import com.sequence.seuqnece_backoffice_server.global.dto.ApiResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/accounts")
@RequiredArgsConstructor
public class AdminAccountController {

    private final AdminSignupService adminSignupService;

    /**
     * 관리자 회원가입 (ROLE_SUPER_ADMIN만 허용)
     */
    @PostMapping
    public ResponseEntity<ApiResponseData<String>> signup(@Valid @RequestBody AdminSignupRequestDto request) {
        adminSignupService.signup(request);
        return ResponseEntity.ok(ApiResponseData.success("관리자 회원가입이 완료되었습니다."));
    }
}