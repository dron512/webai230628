package com.pmh.my.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.pmh.my.filter.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration // spring 객체 설정하는 클래스 입니다.
@EnableWebSecurity // spring security 재정의 하는 클래스입니다.
@RequiredArgsConstructor // @autowrid 안쓰고 private final 설정하는 거 객체 주입합니다.
public class SecuriyConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 보안 토큰 넘어와야 되는데 그거 안하겠다.
                .csrf(csrf -> csrf.disable())
                // 크롬 브라우저 정책상 다른 요청을 허용하지 않는데 그거 안하겠다.
                .cors(cors -> cors.configurationSource(req -> {
                    var cosconfig = new CorsConfiguration();
                    cosconfig.setAllowedOrigins(List.of("*"));
                    cosconfig.setAllowedHeaders(List.of("*"));
                    cosconfig.setAllowedMethods(List.of("*"));
                    return cosconfig;
                }))
                .authorizeHttpRequests(
                        (authorizeRequest) -> 
                            authorizeRequest
                                    // 회원가입 로그인 허용...
                                    .requestMatchers("/auth/**").permitAll()
                                    // 다른것들은 인증이 필요하다...
                                    .anyRequest().authenticated()
                        )
                // session 방식 안하고 JWT 할 계획임
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Username 체크하기 전에 jwtAuthFilter 객체 들어가야함.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
