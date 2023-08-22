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

        log.info("clientName: " + clientName); // Google로 출력된다.
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
        // sub:109522661181196062190 -> 식별키를 loginId로 설정
        // 가입경로+"-"+sub..-> 회원 아이디를 이걸로 처리
        // name:JH K
        // given_name:JH
        // family_name:K
        // picture:https://lh3.googleusercontent.com/a/AAcHTtdHOjj_DdjyUrvZy26lBQxOJLw67Vg4AtWHMJXdGjVX=s96-c
        // email:owojh2@gmail.com
        // email_verified:true
        // locale:ko

        // 고유식별키(sub)를 활용한 회원가입 처리
        String sub = null;

        if(clientName.equals("Google")) { // 구글 로그인을 사용할 경우
            sub = oAuth2User.getAttribute("sub");
        }

        log.info("sub: " + sub);

        // 회원 정보가 있는지 조회(구글회원: google)
        Optional<Member> result = memberRepository.findByLoginId("Google-"+sub, "google");


        if (result.isEmpty()) {
            // 가입된 회원 정보가 없으면 DB에 save 후 인증 처리
            Member member = saveSocialMember(sub);
            // 인증 처리하기
            AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                    member.getLoginId()
                    , member.getPassword()
                    , member.getPath()
                    , member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.name())).collect(Collectors.toList())
                    , oAuth2User.getAttributes());

            authMemberDTO.setName(member.getName());
            return authMemberDTO;
        } else {
            Member member = result.get();
            return new AuthMemberDTO(
                    member.getLoginId()
                    , member.getPassword()
                    , member.getPath()
                    , member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+ role.name())).collect(Collectors.toList())
                    , oAuth2User.getAttributes());
        }


    }

    private Member saveSocialMember(String sub) {
        // 기존에 가입한 적이 있는 경우에는 그대로 조회만 한다.
        Optional<Member> result = memberRepository.findByLoginId("Google-"+sub,"google");


        if(result.isPresent()) { // 기존에 가입한 적이 있는 경우
            return result.get(); // 조회만 함
        }

        // 가입한 정보가 없다면 from=google, loginId=Google-sub, password=@@GOOGLE@@
        Member member = Member.builder()
                .loginId("Google-"+sub)
                .password(passwordEncoder.encode("@@GOOGLE@@"))
                .path("google")
                .build();

        member.addMemberRole(MemberRole.MEMBER);
        memberRepository.save(member);

        return member;
    }
}
