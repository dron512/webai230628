package com.teamntp.firstproject.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Spring Configuration file 선언
@EnableWebSecurity // 내부적으로 SecurityFilterChain 동작, URL 필터가 적용
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() { // BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        // 정적 리소스
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/plugin/**", "/vendor/**").permitAll()
                        // URL 설정
                        .requestMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers("/teacher/**").hasAnyRole("TEACHER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
//                        .authenticated()
                )
                .formLogin(
                        login->login.permitAll()
                )
                .rememberMe((rememberMe) -> rememberMe
                        // 쿠키 유지 시간(초단위)
                        .tokenValiditySeconds(60*60*24*7)
                );

        return http.build();
    }
}
