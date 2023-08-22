package com.teamntp.firstproject.security.config;

import com.teamntp.firstproject.security.handler.MemberLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration // Spring Configuration file 선언
@EnableWebSecurity // 내부적으로 SecurityFilterChain 동작, URL 필터가 적용
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() { // BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // filterChain 설정

        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                // 정적 리소스
//                                .requestMatchers("/css/**", "/js/**", "/images/**", "/plugin/**", "/vendor/**").permitAll()
//                                // URL 설정
//                                .requestMatchers("/student/**").hasAnyRole("STUDENT", "ADMIN", "MANAGER")
//                                .requestMatchers("/teacher/**").hasAnyRole("TEACHER", "ADMIN", "MANAGER")
//                                .requestMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
//                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .anyRequest().permitAll()
//                        .authenticated()
                )
                // 세션 방식 로그인
                .formLogin((login) -> login
                        .loginPage("/member/login")
                        .loginProcessingUrl("/loginProc")
                        .successHandler(memberLoginSuccessHandler())
                        .permitAll()
                )
                // 로그아웃
//                .logout((logout) -> logout
//                        .permitAll())
                // OAuth2
                .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                                .baseUri("/oauth2/authorize"))
                        .successHandler(memberLoginSuccessHandler()))
                // 스프링 시큐리티는 사이트의 콘텐츠가 다른 사이트에 포함되지 않도록 하기 위해 X-Frame-Options 헤더값을 사용하여 이를 방지한다.
                // Click Jacking 공격을 막기위해 사용함
//                .headers((headers) -> headers
//                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
//                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        ;

        return http.build();
    }

    @Bean
    public MemberLoginSuccessHandler memberLoginSuccessHandler() {
        return new MemberLoginSuccessHandler(passwordEncoder());
    }
}
