package com.pmh.my.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // ctrl + space 키 누르면 자동 완성 기능 활성화
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authrization = request.getHeader("authrization");
        System.out.println(authrization);

        if( authrization ==null || !authrization.startsWith("bearer ")){
            System.out.println("auth/login auth/join은 인증모드 없이도 진행해도 됨....");
            System.out.println("왜냐하면 permitAll 해 놨기 때문에...");
            System.out.println("Header 안에 authrization 이 안들어왔네 ...인증모드 없이 진행해라");
            // 그다음꺼 진행해라
            filterChain.doFilter(request, response);
            return;
        }
        // 유효기간 체크 하고 jar 파일 dockerfile 진행하도록
        // AWS 서버구축
        // javasciprt react 

        System.out.println("여기온다.");

         // 인증모드 달아라.....
        UserDetails user = User.builder().username("qwer").password("").build();
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user,
                        null,List.of(new SimpleGrantedAuthority("USER")));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 그다음꺼 진행해라
        filterChain.doFilter(request, response);
    }
}