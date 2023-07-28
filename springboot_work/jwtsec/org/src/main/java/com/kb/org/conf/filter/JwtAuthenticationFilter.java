package com.kb.org.conf.filter;

import com.kb.org.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authrization = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authrization);

        // jwt 토큰 안가지고 왔으면...
        if ( authrization == null || !authrization.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // jwt 토큰을 가지고 왔으면...
        String token = authrization.split(" ")[1];
        System.out.println(token);

        try {
            if (jwtUtils.isExpried(token)) {
                System.out.println("이미 기간 지남");
            }
        }catch (Exception e){
            response.sendRedirect("/auth/login");
            filterChain.doFilter(request,response);
            return;
        }

        // username 가지고 오기...
        // DB 에 가서 username 찾아 가지고 userDetail 만들어야 함..
        UserDetails user = User.builder().username("qwer").password("").build();
        // 인증해라...
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user,
                        null,List.of(new SimpleGrantedAuthority("USER")));
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 그 다음 작업으로 진행해라
        filterChain.doFilter(request,response);
    }
}
