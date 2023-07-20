package com.jpa.org.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//interface A{
//    String doA();
//}
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        A a1 = new A(){
//            @Override
//            public String doA() {
//                return "익명인터페이스로 생성";
//            }
//        };
//        A a2 = ()->{ return "람다로 생성"; };
//        a1.doA();
//        a2.doA();
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    .requestMatchers("/", "/members/**", "/item/**", "/images/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
//                    .requestMatchers("/FreeBoard/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(
                    (form) -> form
                    .loginPage("/login")
                    .permitAll()
            )
            .logout(  (logout) -> logout.permitAll()  );

        return http.build();

    }
}
