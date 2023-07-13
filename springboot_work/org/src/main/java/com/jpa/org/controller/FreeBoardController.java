package com.jpa.org.controller;

import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/FreeBoard")
public class FreeBoardController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("")
    public String index(){
        return "freeboard/index";
    }
}
