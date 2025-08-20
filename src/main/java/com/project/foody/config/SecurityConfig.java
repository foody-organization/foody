package com.project.foody.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());                // 개발 편의상 CSRF 비활성화
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // 모두 허용
        return http.build();
    }
}
