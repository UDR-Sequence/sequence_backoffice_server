package com.sequence.seuqnece_backoffice_server.account.service;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginRequestDto;
import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginResponseDto;

public interface AdminLoginUseCase {
    AdminLoginResponseDto execute(AdminLoginRequestDto requestDto);
}