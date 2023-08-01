package com.pmh.my.config;

import java.util.Date;
import java.util.List;

import com.pmh.my.MyApplication;
import com.pmh.my.service.CustomOauth2UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private final CustomOauth2UserService oAuth2UserService;



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
                                    // user로 요청하는 것은 허용
                                    .requestMatchers("/user/**").permitAll()
                                    // 다른것들은 인증이 필요하다...
                                    // 인증이 필요하기 때문에 google 로그인 페이지로 이동된다.
                                    .anyRequest().authenticated()
                        )
                .oauth2Login((oauth2Login) -> oauth2Login
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userService(oAuth2UserService)
                        )
                        .successHandler(
                            (request,response,authentication)->{
                                // 응답 하는게 문자열이다.
                                response.setContentType("text/html;charset=UTF-8");
                                Claims claims = Jwts.claims();
                                claims.put("username", "pmh");

                                String mytoken = Jwts.builder()
                                        // username 도 추가 해서 암호화 해줘
                                        .setClaims(claims)
                                        // 현재 시간으로 발행되었다.
                                        .setIssuedAt(new Date(System.currentTimeMillis()))
                                        // 5분뒤에 사용할수 없다.
                                        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*5l))
                                        // KEY 값으로 암호화 해라
                                        .signWith(SignatureAlgorithm.HS512, MyApplication.KEY)
                                        .compact();

                                var writer = response.getWriter();
                                writer.println(mytoken);
                            }
                        )
                )
//                .formLogin(fo->fo.loginPage())
                // session 방식 안하고 JWT 할 계획임
                .sessionManagement(ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Username 체크하기 전에 jwtAuthFilter 객체 들어가야함.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                
        return http.build();
    }
}
