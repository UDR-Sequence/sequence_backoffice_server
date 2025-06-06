package com.sequence.sequence_backoffice_server.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
  @Bean
  public AuditorAware<String> auditorAware() {
    return new UserAuditorAware();
  }
}
