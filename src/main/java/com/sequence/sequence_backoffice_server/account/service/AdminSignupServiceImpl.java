package com.sequence.sequence_backoffice_server.account.service;

import com.sequence.sequence_backoffice_server.account.dto.AdminSignupRequestDto;
import com.sequence.sequence_backoffice_server.account.entity.Admin;
import com.sequence.sequence_backoffice_server.account.repository.AdminRepository;
import com.sequence.sequence_backoffice_server.security.enums.AdminRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminSignupServiceImpl implements AdminSignupUseCase {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void execute(AdminSignupRequestDto requestDto) {
        if (adminRepository.existsByUsername(requestDto.username())) {
            throw new IllegalArgumentException("이미 존재하는 관리자 ID입니다.");
        }

        Admin admin = Admin.builder()
                .username(requestDto.username())
                .password(passwordEncoder.encode(requestDto.password()))
                .name(requestDto.name())
                .email(requestDto.email())
                .adminRole(AdminRole.from(requestDto.adminRole()))
                .build();

        adminRepository.save(admin);
    }
}