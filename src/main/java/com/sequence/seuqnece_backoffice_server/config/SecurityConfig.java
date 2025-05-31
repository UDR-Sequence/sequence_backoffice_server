package com.sequence.seuqnece_backoffice_server.config;

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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
//    private final JWTUtil jwtUtil;
//    private final TokenReissueService tokenReissueService;
//    private final RefreshRepository refreshRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        // Custom LoginFilter 등록
//        LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, tokenReissueService, memberRepository);
//        loginFilter.setFilterProcessesUrl("/api/login"); // 엔드포인트를 /api/login으로 변경

        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration configuration = new CorsConfiguration();
                                configuration.setAllowedOrigins(Arrays.asList(
                                        "http://localhost:3000"
                                ));
                                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                                configuration.setAllowCredentials(true);

                                configuration.setAllowedHeaders(Collections.singletonList("*"));
                                configuration.setExposedHeaders(Arrays.asList("Authorization", "access"));
                                return configuration;
                            }
                        }))
                .csrf((csrf) -> csrf.disable()); // 필요에 따라 유지

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //form 로그인 방식 disable
        http
                .formLogin((auth)->auth.disable());


        //http basic 인증 방식 disable
        http
                .httpBasic((auth)->auth.disable());

        http
                .logout((auth)->auth.disable()); // 기본 로그아웃 필터 비활성화


        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/api/admin/**").authenticated()
                        .anyRequest().permitAll());

//        http
//                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
//
//        //usernamePasswordAuthenticationFilter를 대신하여 우리가 로그인 필터를 작성하였기 때문에, (폼 로그인 방식을 disabled 하였기 때문)
//        //usernamePasswordAuthenticationFilter자리에 로그인필터를 등록해준다(addFilter 사용)
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, tokenReissueService,memberRepository), UsernamePasswordAuthenticationFilter.class);
//
//        http
//                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class);
//
        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();

    }

}