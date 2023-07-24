package com.jpa.org.conf;

import com.jpa.org.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/Member/**", "/item/**", "/images/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/account/**").permitAll()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
            )
            .formLogin(
                (form) -> form
                    .loginPage("/account/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/account/login?error")
                .permitAll()
            )
            .logout(  (logout) -> logout
//                    .logoutUrl("/account/se")
//                    .addLogoutHandler(((request, response, authentication) -> {
//                        HttpSession session = request.getSession();
//                        if(session!=null){
//                            session.invalidate();
//                        }
//                    }))
//                    .logoutSuccessHandler(((request, response, authentication) -> {
//                        response.sendRedirect("/");
//                    }))
                    .permitAll()  );

        return http.build();
    }


}
