package com.teamntp.firstproject.course.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping
public class UploadTestController {
    @GetMapping("/uploadEx")
    public void uploadEx() { // upload test page
        log.info("/uploadEx...............................");
    }

}
