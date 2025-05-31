package com.sequence.seuqnece_backoffice_server.security.service;

import com.sequence.seuqnece_backoffice_server.account.entity.Admin;
import com.sequence.seuqnece_backoffice_server.account.repository.AdminRepository;
import com.sequence.seuqnece_backoffice_server.global.exception.UserNotFindException;
import com.sequence.seuqnece_backoffice_server.security.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFindException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(UserNotFindException::new);
        return new CustomUserDetails(admin);
    }
}
