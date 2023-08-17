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

        // 소셜 회원인지 확인
        boolean fromSocial = authMemberDTO.isFromSocial();

        log.info("Need Modify Member?" + fromSocial);

        request.setAttribute("AuthMemberDTO",authMemberDTO);

        redirectStrategy.sendRedirect(request,response,"/memeber/snsjoin");


        // 비밀번호가 "1111"과 일치하는지 확인
        boolean passwordResult = passwordEncoder.matches("1111", authMemberDTO.getPassword());
//
//        if(fromSocial && passwordResult) { // 소셜 회원이고 비밀번호가 "1111"이라면
//            // todo sub이 중복이 아닐때는 자동회원가입처리
//            // sub이 중복일 때는 로그인 한 적이 있고, 회원가입이 되어진 적이 있기 때문에
//            // 얘는 바로 인증 처리.....
//            redirectStrategy.sendRedirect(request,response,"/sample/member/modify?from=social");
//            // 일단 화면 대충 설정만 해둠 헤ㅔ헿..신난다
//            // 회원가입 페이지로 보내면 될 듯
//        }
//
//
//        // 폼 로그인 방식인 경우 분기 처리
//        if(!fromSocial) {
//            if(authentication.getAuthorities().toString().equals("[ROLE_STUDENT]")) {
//                redirectStrategy.sendRedirect(request,response,"/sample/student");
//            } else if (authentication.getAuthorities().toString().equals("[ROLE_TEACHER]")) {
//                redirectStrategy.sendRedirect(request,response,"/sample/teacher");
//            } else if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
//                redirectStrategy.sendRedirect(request,response,"/sample/admin");
//            }
//        }
    }
}
