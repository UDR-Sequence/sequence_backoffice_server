package com.sequence.sequence_backoffice_server.account.service;

import com.sequence.sequence_backoffice_server.account.dto.AdminLoginRequestDto;
import com.sequence.sequence_backoffice_server.account.dto.AdminLoginResponseDto;

public interface AdminLoginUseCase {
    AdminLoginResponseDto execute(AdminLoginRequestDto requestDto);
}