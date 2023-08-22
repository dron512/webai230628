package com.teamntp.firstproject.member.controller;

import com.teamntp.firstproject.member.dto.MemberSignUpDTO;
import com.teamntp.firstproject.member.entity.Member;
import com.teamntp.firstproject.member.entity.MemberRole;
import com.teamntp.firstproject.member.repository.MemberRepository;
import com.teamntp.firstproject.member.service.MemberService;
import com.teamntp.firstproject.security.dto.AuthMemberDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/member/")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public void gotoLogin() {
        log.info("/member/login.........................................");
    }

    @GetMapping("/sign")
    public void gotoSign(@ModelAttribute MemberSignUpDTO memberSignUpDTO) {
        log.info("/member/sign....................................");
        log.info("MemberSignUpDTO" + memberSignUpDTO);
        log.info("path" + memberSignUpDTO.getPath());
    }

    @GetMapping("/signup")
    public void gotoSignup(@ModelAttribute @Valid MemberSignUpDTO memberSignUpDTO
            , BindingResult bindingResult, Principal principal) {

        log.info("/member/signup.......................");
        log.info("MemberSignUpDTO: " + memberSignUpDTO);
        log.info("type: " + memberSignUpDTO.getType());
        log.info(principal);
        AuthMemberDTO authMemberDTO = (AuthMemberDTO) principal;
        memberSignUpDTO.setLoginId(authMemberDTO.getLoginId());
        // sign page 에서 넘어온 path, type 정보를 createMember 로 같이 보내줘야 함
        // input type=hidden 으로 path, type 정보 넘겨주고 post 방식으로 받아서 db에 save 함
    }

    /*
    @PostMapping("/signup")
    public ResponseEntity<String> createMember(@ModelAttribute @Valid MemberSignUpDTO memberSignUpDTO
            , BindingResult bindingResult) {
        log.info("==============================================================");
        log.info("==============================================================");
        log.info("/member/signup method post..........................");
        log.info("MemberSignUpDTO: " + memberSignUpDTO);
        log.info("==============================================================");
        log.info("==============================================================");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
     */

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String createMember(@ModelAttribute @Valid MemberSignUpDTO memberSignUpDTO
            , BindingResult bindingResult) {
        log.info("==============================================================");
        log.info("==============================================================");
        log.info("/member/signup method post..........................");
        log.info("MemberSignUpDTO: " + memberSignUpDTO);

        Optional<Member> result = memberRepository.findByLoginId("Google-"+memberSignUpDTO.getLoginId(),"google");


        if(result.isPresent()) { // 기존에 가입한 적이 있는 경우
//            return result.get(); // 조회만 함
        }

        Member member = Member.builder()
                .type(memberSignUpDTO.getType())
                .loginId(memberSignUpDTO.getLoginId())
                .build();
        if (member != null) {
            if (memberSignUpDTO.getType().equals("teacher"))
                member.addMemberRole(MemberRole.TEACHER);
            else if (memberSignUpDTO.getType().equals("student"))
                member.addMemberRole(MemberRole.STUDENT);
            else if (memberSignUpDTO.getType().equals("admin"))
                member.addMemberRole(MemberRole.ADMIN);

//            member.pw(passwordEncoder.encode("@@GOOGLE@@"));
            memberRepository.save(member);
        }
        log.info("==============================================================");
        log.info("==============================================================");
        return "redirect:/member/login";
    }
}
