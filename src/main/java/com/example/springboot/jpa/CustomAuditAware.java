package com.example.springboot.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Ahielg
 * @date 27/12/2020
 */
@Configuration
public class CustomAuditAware  implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ahiel");
        //return Optional.empty();
    }
}
