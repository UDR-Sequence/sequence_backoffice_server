package com.sequence.seuqnece_backoffice_server.account.controller;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminSignupRequestDto;
import com.sequence.seuqnece_backoffice_server.account.service.AdminSignupUseCase;
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
public class AdminAccountSignUpController {

    private final AdminSignupUseCase adminSignupService;

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponseData<Void>> signup(
            @Valid @RequestBody AdminSignupRequestDto requestDto) {
        adminSignupService.execute(requestDto);
        return ResponseEntity
                .ok()
                .body(ApiResponseData.success(null));
    }
}