package com.teamntp.firstproject.security.service;

import com.teamntp.firstproject.member.entity.Member;
import com.teamntp.firstproject.member.entity.MemberRole;
import com.teamntp.firstproject.member.repository.MemberRepository;
import com.teamntp.firstproject.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberOAuth2UserDetailsService extends DefaultOAuth2UserService {

    // 필요한 객체를 주입받는 방식으로 변경
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("====================================================");
        // log: org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@79549844
        log.info("UserRequest: " + userRequest);
        // userRequest: org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest 객체

        // 소셜 로그인한 사용자 정보를 조회
        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName); // Google로 출력
        log.info("AdditionalParameters: " + userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("=====================================================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            // sub, name, given_name, family_name, picture, email, email_verified, locale 이 출력된다.
            log.info("key:value" + k + ":" + v);
        });
        // 로그창 내용
        // ====================================================
        // UserRequest: org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@7ef6bda0
        // clientName: Google
        // AdditionalParameters: {id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxMWUzOWUyNzkyOGFlOWYxZTlkMWUyMTY0NmRlOTJkMTkzNTFiNDQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyNTQ0Mjk1NDg5MTEtOTRmZHZybXFlZmRwdDE0OTJwdW1rNGJhcW1kdTBubm0uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyNTQ0Mjk1NDg5MTEtOTRmZHZybXFlZmRwdDE0OTJwdW1rNGJhcW1kdTBubm0uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDk1MjI2NjExODExOTYwNjIxOTAiLCJlbWFpbCI6Im93b2poMkBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IjQzNXJVaWFJYk95QjVKV0tZOEhQZEEiLCJuYW1lIjoiSkggSyIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQWNIVHRkSE9qal9EZGp5VXJ2WnkyNmxCUXhPSkx3NjdWZzRBdFdITUpYZEdqVlg9czk2LWMiLCJnaXZlbl9uYW1lIjoiSkgiLCJmYW1pbHlfbmFtZSI6IksiLCJsb2NhbGUiOiJrbyIsImlhdCI6MTY5MTU0MzQ5NiwiZXhwIjoxNjkxNTQ3MDk2fQ.Wv8D_S4GvUOY7vVPYxDtk0swj4L1eJysQTsaV8ZHCJrTFpj7zwXf8frCTpCNyuAPE5fIkf9SwSuqyQ5o8HKyNINyER_kZZLUW4y0IGiSJm3aS9s2sSE3gfwokFSsI4A237lFlRPLdDS23-NwGumjtAf_HLVzxVKgT1KwMj-EoXM310BZPafkdwYawyqqfKbMnHjFCohkMZOyGyzGR7l-C0M9A_SoH5pT3D1APYuDH7LG_XYEvefvffCyna0AQ2mZADgSC44JQo-kWQX6tJm-k-LBMoIZvTkhLV7o0ouu1tnu8zL0I66auQvY6VlKf1WCTMwCZkWx8IfOg2yDsj9Meg}
        // =====================================================

        // loadUser() 에서 사용하는 OAuth2UserRequest 는 현재 어떤 서비스를 통해서 로그인이 이루어졌는지 알아내고
        // 전달된 값들을 추출할 수 있는 데이터를 Map<String, Object> 의 형태로 사용할 수 있다.
        // key:value
        // sub:109522661181196062190 -> 식별키 아아.. 회원가입이
        // 가입경로+"-"+sub..-> 회원 아이디를 이걸로 처리
        // name:JH K
        // given_name:JH
        // family_name:K
        // picture:https://lh3.googleusercontent.com/a/AAcHTtdHOjj_DdjyUrvZy26lBQxOJLw67Vg4AtWHMJXdGjVX=s96-c
        // email:owojh2@gmail.com
        // email_verified:true
        // locale:ko

        // 이메일을 활용한 회원가입 처리
        String loginId = null;

        if(clientName.equals("Google")) { // 구글 로그인을 사용할 경우
            loginId = oAuth2User.getAttribute("email");
        }

        log.info("loginId" + loginId);

        // 이따가 사용
        /* Member member = saveSocialMember(loginId); // 해당 클래스에 선언된 saveSocialMember() method 확인할 것
        return oAuth2User;*/

        Member member = saveSocialMember(loginId);
        
        //인증 달아주는건데

        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getLoginId()
                , member.getPassword()
                ,true
                , member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.name())).collect(Collectors.toList())
                , oAuth2User.getAttributes());

        authMemberDTO.setName(member.getName());

        return authMemberDTO;

    }

    private Member saveSocialMember(String loginId) {
        // 기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만 한다.
        Optional<Member> result = memberRepository.findByLoginId(loginId, true);

        if(result.isPresent()) {
            return result.get();
        }

        // 가입한 정보가 없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode("1111"))
//                .fromSocial(true)
                .build();
        member.addMemberRole(MemberRole.STUDENT); // 일단 학생으로 처리
        memberRepository.save(member);

        return member;
    }
}
