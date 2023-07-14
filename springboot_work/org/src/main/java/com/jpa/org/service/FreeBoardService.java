package com.jpa.org.service;

import com.jpa.org.dto.FreeBoardDto;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardService {
    @Autowired
    FreeBoardRepository freeBoardRepository;
    public List<FreeBoardDto> list() {
        Page<FreeBoard> pagelist = freeBoardRepository.findAll(
                                    PageRequest.of(0,5));
//        List<FreeBoard> dblist = pagelist.stream().toList();
        List<FreeBoardDto> dtolist = new ArrayList<>();
        for( FreeBoard fb :pagelist){
            FreeBoardDto dto = FreeBoardDto.of(fb);
            dtolist.add(dto);
        }
        System.out.println("dtolist = "+dtolist);
        return dtolist;
    }
}
