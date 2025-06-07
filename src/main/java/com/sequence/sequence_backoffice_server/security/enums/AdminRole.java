package com.sequence.sequence_backoffice_server.security.enums;

import com.sequence.sequence_backoffice_server.global.exception.BadRequestException;
import java.util.Arrays;

public enum AdminRole {
        ROLE_ADMIN,
        ROLE_SUPER_ADMIN;

        public static AdminRole from(String value) {
                return Arrays.stream(AdminRole.values())
                        .filter(role -> role.name().equalsIgnoreCase(value))
                        .findFirst()
                        .orElseThrow(() -> new BadRequestException("잘못된 관리자 역할입니다: " + value));
        }
}