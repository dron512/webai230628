package com.jpa.org.controller;

import com.jpa.org.entity.FreeBoard;
import com.jpa.org.entity.Role;
import com.jpa.org.repository.FreeBoardRepository;
import com.jpa.org.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String index(
//            @RequestParam(required = false) Authentication authentication
    ){
        Role role1 = Role.builder().idx(1).name("USER").build();
        Role role2 = Role.builder().idx(2).name("ADMIN").build();
        roleRepository.save(role1);
        roleRepository.save(role2);
//        if (authentication != null) {
//            System.out.println("로그인유무 = " + authentication.isAuthenticated());
//            System.out.println("getPrincipal = " + authentication.getPrincipal());
//            System.out.println("userdetails = " + authentication.getDetails());
//        }
//        FreeBoard freeBoard = freeBoardRepository.myQuery(1);
//        System.out.println(freeBoard);
//        FreeBoard f1 = new FreeBoard().builder()
//                .name("홍길동")
//                .content("내요내욘ㅇ")
//                .build();
//        freeBoardRepository.save(f1);
        return "index";
    }
}
