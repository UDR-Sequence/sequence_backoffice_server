package com.sequence.seuqnece_backoffice_server.account;

import com.sequence.seuqnece_backoffice_server.entity.BaseTimeEntity;
import com.sequence.seuqnece_backoffice_server.security.enums.AdminRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username; // 로그인 ID

    @Column(nullable = false)
    private String password; // 암호화 저장

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role", nullable = false)
    private AdminRole adminRole;
}
