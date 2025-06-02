package com.sequence.seuqnece_backoffice_server.account.service;

import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginRequestDto;
import com.sequence.seuqnece_backoffice_server.account.dto.AdminLoginResponseDto;
import com.sequence.seuqnece_backoffice_server.account.entity.Admin;
import com.sequence.seuqnece_backoffice_server.account.repository.AdminRepository;
import com.sequence.seuqnece_backoffice_server.global.exception.Code;
import com.sequence.seuqnece_backoffice_server.global.exception.BaseException;
import com.sequence.seuqnece_backoffice_server.security.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminLoginService {

    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;

    public AdminLoginResponseDto login(AdminLoginRequestDto requestDto) {
        try {
            // 1. Spring Security 인증 처리
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword()));

            // 2. SecurityContext에 인증 정보 저장
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            // 3. HttpSession 생성 및 SecurityContext 저장
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            // 4. 인증된 사용자 정보 조회
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Admin admin = userDetails.getAdmin();

            // 5. 응답 DTO 생성
            return AdminLoginResponseDto.builder()
                    .name(admin.getName())
                    .adminRole(admin.getAdminRole())
                    .build();

        } catch (Exception e) {
            throw new BaseException(Code.INVALID_CREDENTIALS);
        }
    }
}