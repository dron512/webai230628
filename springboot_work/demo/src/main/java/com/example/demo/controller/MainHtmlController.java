package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainHtmlController {

    @GetMapping("aa")
    public String aa(
            Model model,
            String num1,
            String num2){
        System.out.println("출력");
        System.out.println(num1);
        System.out.println(num2);
        if (num1 == null) num1 = "0";
        if (num2 == null) num2 = "0";
        model.addAttribute(
                "data",
                "asdfasdf");
        model.addAttribute(
                "result",
                Integer.parseInt(num1)+
                        Integer.parseInt(num2));
        return "aa";
    }
}
