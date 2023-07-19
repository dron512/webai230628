package com.jpa.org.controller;

import com.jpa.org.dto.FreeBoardDto;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import com.jpa.org.service.FreeBoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/FreeBoard")
public class FreeBoardController {

    @Autowired
    FreeBoardService freeBoardService;

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @DeleteMapping("Delete")
    public @ResponseBody String delete(FreeBoardDto freeBoardDto){
        freeBoardRepository.deleteById(freeBoardDto.getIdx());
        return "success";
    }

    @GetMapping("View")
    public String view(@ModelAttribute @Valid FreeBoardDto freeBoardDto,
                       BindingResult bindingResult,
                       Model model){
        System.out.println("idx = "+freeBoardDto.getIdx());

        FreeBoardDto dto = freeBoardService.getRow(freeBoardDto);
        model.addAttribute("freeBoardDto",dto);
        return "freeboard/view";
    }


    @GetMapping("WriteForm")
    public String writeForm(@ModelAttribute @Valid FreeBoardDto freeBoardDto,BindingResult bindingResult){
        return "freeboard/writeform";
    }

    @GetMapping("UpdateForm")
    public String UpdateForm(@ModelAttribute @Valid FreeBoardDto freeBoardDto,
                             BindingResult bindingResult,
                             Model model){
        System.out.println(freeBoardDto);
        FreeBoardDto dto = freeBoardService.getRow(freeBoardDto);
        model.addAttribute("freeBoardDto",dto);
        return "freeboard/updateform";
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

    @PostMapping("UpdateForm")
    public String pUpdateForm(@ModelAttribute @Valid FreeBoardDto freeBoardDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "freeboard/updateform";
        }else{
            boolean result = freeBoardService.insert(freeBoardDto);
            if(result)
                return "redirect:/FreeBoard";
        }
        return "freeboard/updateform";
    }

    @GetMapping("")
    public String index(Model model, @PageableDefault(
            size = 5,
            sort = "idx",
            direction = Sort.Direction.DESC,
            page = 0) Pageable pageable,
            @RequestParam(required = false, defaultValue = "0") int page){
        Page<FreeBoard> pagelist = freeBoardService.list(pageable);

        // 총 행갯수
        System.out.println(pagelist.getTotalElements());
        // 총 페이지 갯수
        System.out.println(pagelist.getTotalPages());

        List<FreeBoardDto> dtolist = new ArrayList<>();
        for( FreeBoard fb :pagelist){
            FreeBoardDto dto = FreeBoardDto.of(fb);
            dtolist.add(dto);
        }
        model.addAttribute("curPage",page+1);
        model.addAttribute("totalElements",pagelist.getTotalElements());
        model.addAttribute("totalPages",pagelist.getTotalPages());

        model.addAttribute("list",pagelist);
        return "freeboard/index";
    }

}
