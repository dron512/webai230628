package com.pmh.my.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    // 로그인 하면 Authentication 객체 만들어진
    @GetMapping("user")
    public String user(Authentication authentication){
        System.out.println("test");

        System.out.println(authentication.getPrincipal());
        return authentication.getName();
    }
}
