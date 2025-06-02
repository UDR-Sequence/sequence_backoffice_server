package com.sequence.seuqnece_backoffice_server.account.dto;

import com.sequence.seuqnece_backoffice_server.security.enums.AdminRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminLoginResponseDto {
    private String name;
    private AdminRole adminRole;
}