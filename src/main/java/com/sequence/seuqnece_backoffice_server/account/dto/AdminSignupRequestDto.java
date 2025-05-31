package com.sequence.seuqnece_backoffice_server.account.dto;

public record AdminSignupRequestDto(
        String username,
        String password,
        String name,
        String email,
        String adminRole // 예: ROLE_ADMIN 또는 ROLE_SUPER_ADMIN
) {}
