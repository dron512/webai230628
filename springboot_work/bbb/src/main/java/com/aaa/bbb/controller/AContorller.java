package com.aaa.bbb.controller;

import com.aaa.bbb.myclass.AA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    @RestController
    -> 주소를 호출하면 응답하는 클래스
    @GetMapping
    -> 어떤주소를 요청하면 어떤결과

    @Component
    -> 스프링객체통안에 이 객체 넣겠다
    @Autowird
    -> 스프링객체통안에 있는 객체를 가져와서 초기화하겠다.
 */
@RestController
public class AContorller {

    @Autowired
    AA aa3;
    @Autowired
    AA aa4;

    @GetMapping("aa")
    public String aa(){
        AA aa1 = new AA();
        AA aa2 = new AA();
        System.out.println("aa1 = "+aa1);
        System.out.println("aa2 = "+aa2);

        System.out.println("aa1.a = "+aa1.a);
        System.out.println("aa2.a = "+aa2.a);

        aa1.a = 20;

        System.out.println("aa1.a = "+aa1.a);
        System.out.println("aa2.a = "+aa2.a);

        System.out.println("aa3 = "+aa3.a);
        System.out.println("aa4 = "+aa4.a);

        aa3.a = 40;

        System.out.println("aa3 = "+aa3.a);
        System.out.println("aa4 = "+aa4.a);

        return "qweqweqweqwe";
    }

    @GetMapping("bb")
    public String bb(){
        return "bb";
    }
}
