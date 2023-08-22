package com.teamntp.firstproject.security.handler;

import com.teamntp.firstproject.security.dto.AuthMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler {
    // RedirectStrategy(인터페이스) -> DefaultRedirectStrategy(구현 클래스)
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    // PasswordEncoder 주입 -> 소셜 로그인한 비밀번호가 "1111"인 경우 회원 정보를 변경할 수 있도록 하기 위헤서
    private PasswordEncoder passwordEncoder;

    // 생성자
    public MemberLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("========================================================");
        log.info("on AuthenticationSuccess ");

        // authentication 정보 가져오기
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) authentication.getPrincipal();

        log.info("=========================================================");
        log.info("authMemberDTO -> " + authMemberDTO);
        log.info("authentication.getDetails().toString() -> " + authentication.getAuthorities().toString());

        // 자체회원인지 구글회원 확인할 때 필요한 변수들
        String path = authMemberDTO.getPath(); // form(자체), google(구글)
        boolean passwordChecker = passwordEncoder.matches("@@GOOGLE@@", authMemberDTO.getPassword());

        log.info("This Member is from " + path);


        // 자체 회원인지 Google 회원인지 확인
        if (path.equals("form")) { // 자체 회원, authentication.getAuthorities().toString().equals("[ROLE_STUDENT]")
            if (authentication.getAuthorities().contains("STUDENT")) {
                redirectStrategy.sendRedirect(request, response, "/student");
            } else if (authentication.getAuthorities().contains("TEACHER")) {
                redirectStrategy.sendRedirect(request, response, "/teacher");
            } else if (authentication.getAuthorities().contains("ADMIN")) {
                redirectStrategy.sendRedirect(request, response, "/admin");
            } else if (authentication.getAuthorities().contains("MANAGER")) {
                redirectStrategy.sendRedirect(request, response, "/manager");
            }
        }

        if (path.equals("google")) { // 소셜 회원
            if(!passwordChecker) {
                if (authentication.getAuthorities().contains("STUDENT")) {
                    redirectStrategy.sendRedirect(request, response, "/student");
                } else if (authentication.getAuthorities().contains("TEACHER")) {
                    redirectStrategy.sendRedirect(request, response, "/teacher");
                } else if (authentication.getAuthorities().contains("ADMIN")) {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } else if (authentication.getAuthorities().contains("MANAGER")) {
                    redirectStrategy.sendRedirect(request, response, "/manager");
                } else if (authentication.getAuthorities().contains("MEMBER")) {
                    redirectStrategy.sendRedirect(request, response, "/teacher");
                }
            }
            if(passwordChecker) {
                request.setAttribute("AuthMemberDTO",authMemberDTO);
                redirectStrategy.sendRedirect(request,response,"/member/sign?path=google");
            }
        }

    }
}
