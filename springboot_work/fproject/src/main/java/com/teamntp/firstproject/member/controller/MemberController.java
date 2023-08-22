package com.teamntp.firstproject.member.controller;

import com.teamntp.firstproject.member.dto.MemberSignUpDTO;
import com.teamntp.firstproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            , BindingResult bindingResult) {
        log.info("/member/signup.......................");
        log.info("MemberSignUpDTO: " + memberSignUpDTO);
        log.info("type: " + memberSignUpDTO.getType());
        // sign page 에서 넘어온 path, type 정보를 createMember 로 같이 보내줘야 함
        // input type=hidden 으로 path, type 정보 넘겨주고 post 방식으로 받아서 db에 save 함
    }

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
}
