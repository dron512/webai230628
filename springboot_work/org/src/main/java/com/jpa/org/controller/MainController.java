package com.jpa.org.controller;

import com.jpa.org.entity.FreeBoard;
import com.jpa.org.entity.Role;
import com.jpa.org.repository.FreeBoardRepository;
import com.jpa.org.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final FreeBoardRepository freeBoardRepository;

    private final RoleRepository roleRepository;

    @GetMapping("/")
    public String index(
//            @RequestParam(required = false) Authentication authentication
    ){

        roleRepository.save(new Role(1,"User"));
        roleRepository.save(new Role(2,"Admin"));
        roleRepository.save(new Role(3,"Manager"));

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
