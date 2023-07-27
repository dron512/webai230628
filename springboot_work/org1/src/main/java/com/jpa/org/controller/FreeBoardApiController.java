package com.jpa.org.controller;

import com.jpa.org.dto.FreeBoardDto;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class FreeBoardApiController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("list")
    List<FreeBoard> list(){
        return freeBoardRepository.findAll();
    }

    @PostMapping("Delete")
    public @ResponseBody String delete(FreeBoardDto freeBoardDto){
        freeBoardRepository.deleteById(freeBoardDto.getIdx());
        return "success";
    }

}
