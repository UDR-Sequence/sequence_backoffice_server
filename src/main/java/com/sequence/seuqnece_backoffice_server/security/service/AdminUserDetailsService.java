package com.sequence.seuqnece_backoffice_server.security.service;

import com.sequence.seuqnece_backoffice_server.account.entity.Admin;
import com.sequence.seuqnece_backoffice_server.account.repository.AdminRepository;
import com.sequence.seuqnece_backoffice_server.global.exception.Code;
import com.sequence.seuqnece_backoffice_server.global.exception.BaseException;
import com.sequence.seuqnece_backoffice_server.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(Code.ACCOUNT_NOT_FOUND));

        return new CustomUserDetails(admin);
    }
}
