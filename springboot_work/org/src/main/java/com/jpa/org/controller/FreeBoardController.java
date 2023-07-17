package com.jpa.org.controller;

import com.jpa.org.dto.FreeBoardDto;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.service.FreeBoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/FreeBoard")
public class FreeBoardController {

    @Autowired
    FreeBoardService freeBoardService;

    @GetMapping("View")
    public String view(@ModelAttribute @Valid FreeBoardDto freeBoardDto,BindingResult bindingResult){
        return "freeboard/view";
    }


    @GetMapping("WriteForm")
    public String writeForm(@ModelAttribute @Valid FreeBoardDto freeBoardDto,BindingResult bindingResult){
        return "freeboard/writeform";
    }

    @PostMapping("WriteForm")
    public String pwriteForm(@ModelAttribute @Valid FreeBoardDto freeBoardDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            System.out.println("사이즈 문제 있음");
//            model.addAttribute("freeboarddto",dto);
            return "freeboard/writeform";
        }else{
            System.out.println(freeBoardDto);
            boolean result = freeBoardService.insert(freeBoardDto);
            if(result)
                return "redirect:/FreeBoard";
        }
        return "freeboard/writeform";
    }

    @GetMapping("")
    public String index(Model model, @PageableDefault(
            size = 5,
            sort = "idx",
            direction = Sort.Direction.DESC,
            page = 0) Pageable pageable){
        List<FreeBoardDto> list = freeBoardService.list(pageable);
        System.out.println(list);
        model.addAttribute("list",list);
        return "freeboard/index";
    }

}
