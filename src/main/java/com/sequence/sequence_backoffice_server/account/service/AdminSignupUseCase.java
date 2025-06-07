package com.sequence.sequence_backoffice_server.account.service;

import com.sequence.sequence_backoffice_server.account.dto.AdminSignupRequestDto;

public interface AdminSignupUseCase {
    void execute(AdminSignupRequestDto requestDto);
}