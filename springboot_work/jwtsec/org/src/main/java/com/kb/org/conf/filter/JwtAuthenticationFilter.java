package com.kb.org.conf.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("일로오나");
        // 그냥 다음꺼 진행해라
//        filterChain.doFilter(request,response);
        /*
            로그인 되어 있는지 확인 JWT 가 왔는지 확인...
            모든 인증 없음.... 모든 유저 허용... username= qwer로... 권한은 user로...
        */
        UserDetails user = User.builder().username("qwer").password("").build();

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user,
                        null,List.of(new SimpleGrantedAuthority("USER")));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }
}
