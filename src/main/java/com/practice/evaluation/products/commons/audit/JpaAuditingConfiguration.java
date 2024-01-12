package com.practice.evaluation.products.commons.audit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @apiNote JpaAuditingConfiguration, Gestiona la configuración de los campos que serán audtiables
 *
 * @version 1.0.0
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Value("${spring.datasource.username}")
    private String userDB;

    @Bean
    public AuditorAware<String> auditorProvider() {

        return () -> Optional.ofNullable(userDB);
    }
}
