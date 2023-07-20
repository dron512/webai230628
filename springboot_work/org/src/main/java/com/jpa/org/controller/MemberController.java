package com.jpa.org.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

    // 회원가입 화면
    @GetMapping("signup")
    public String signup(){
        return "member/signup";
    }

    // 화원가입 해서 테이블에다가 저장을 해야 합니다.
    @PostMapping("signup")
    public String psignup(){
        return "redirect:/";
    }
}
