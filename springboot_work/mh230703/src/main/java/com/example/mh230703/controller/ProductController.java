package com.example.mh230703.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @PostMapping("insert")
    public String insert(){
        System.out.println("일로오나");
        return "redirect:/";
    }

}
