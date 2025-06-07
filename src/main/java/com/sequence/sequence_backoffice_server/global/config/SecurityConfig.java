package com.sequence.sequence_backoffice_server.global.config;

import com.sequence.sequence_backoffice_server.security.service.AdminUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final AdminUserDetailsService userDetailsService;

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .cors((cors) -> cors
                                                .configurationSource(new CorsConfigurationSource() {
                                                        @Override
                                                        public CorsConfiguration getCorsConfiguration(
                                                                        HttpServletRequest request) {
                                                                CorsConfiguration configuration = new CorsConfiguration();
                                                                configuration.setAllowedOrigins(Arrays.asList(
                                                                                "http://localhost:3000",
                                                                                "https://parkdu7.github.io",
                                                                                "https://sequence-zeta.vercel.app"));
                                                                configuration.setAllowedMethods(Arrays.asList("GET",
                                                                                "POST", "PUT", "DELETE", "OPTIONS"));
                                                                configuration.setAllowCredentials(true);
                                                                configuration.setAllowedHeaders(
                                                                                Collections.singletonList("*"));
                                                                return configuration;
                                                        }
                                                }))
                                .csrf((csrf) -> csrf.disable())
                                .formLogin(form -> form.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))
                                .securityContext(context -> context
                                                .securityContextRepository(new HttpSessionSecurityContextRepository()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/admin/login", "/api/admin/accounts").permitAll()
                                                .anyRequest().authenticated())
                                .userDetailsService(userDetailsService);

                return http.build();
        }
}
