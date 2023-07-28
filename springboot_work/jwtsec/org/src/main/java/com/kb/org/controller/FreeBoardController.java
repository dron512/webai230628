package com.kb.org.controller;

import com.kb.org.model.FreeBoard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("freeboard")
public class FreeBoardController {

    @GetMapping("select")
    public ResponseEntity<FreeBoard> select(){
        // db에서 가져와야함..
        FreeBoard freeBoard = new FreeBoard(1,
                                    "홍길동",
                                    "내용",
                                    "제목");
        return ResponseEntity.ok().body(freeBoard);
    }
}
