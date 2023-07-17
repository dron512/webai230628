package com.jpa.org.service;

import com.jpa.org.dto.FreeBoardDto;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardService {
    @Autowired
    FreeBoardRepository freeBoardRepository;
    public List<FreeBoardDto> list(Pageable pageable) {
//        Pageable pageable = PageRequest.of(0,5, Sort.by("idx").descending());
        Page<FreeBoard> pagelist = freeBoardRepository.findAll(pageable);
        List<FreeBoardDto> dtolist = new ArrayList<>();
        for( FreeBoard fb :pagelist){
            FreeBoardDto dto = FreeBoardDto.of(fb);
            dtolist.add(dto);
        }
        return dtolist;
    }

    public boolean insert(FreeBoardDto dto) {
        FreeBoard freeBoard = dto.createFreeBoard();
        freeBoardRepository.save(freeBoard);
        return true;
    }

}
