package com.sequence.seuqnece_backoffice_server.account.service;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminSignupRequestDto;
import com.sequence.seuqnece_backoffice_server.account.entity.Admin;
import com.sequence.seuqnece_backoffice_server.account.repository.AdminRepository;
import com.sequence.seuqnece_backoffice_server.security.enums.AdminRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminSignupService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void signup(AdminSignupRequestDto dto) {
        if (adminRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("이미 존재하는 관리자 ID입니다.");
        }

        Admin admin = Admin.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .name(dto.name())
                .email(dto.email())
                .adminRole(AdminRole.from(dto.adminRole()))
                .build();

        adminRepository.save(admin);
    }
}