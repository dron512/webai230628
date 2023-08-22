package com.teamntp.firstproject.common.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> { // 제네릭 타입, DTO와 ENTITY 타입 ***
    // DTO list
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 시작 페이지 번호, 끝 페이지 번호
    private int start, end;

    // 이전, 다음
    private boolean prev, next;

    private List<Integer> pageList;


    // Function<EN,DTO> : entity -> DTO 변환 ***
    // Page<Entity> 타입을 이용해서 생성할 수 있도록 생성자로 작성 ***
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        // totalPage 여기서 지정
        totalPage = result.getTotalPages();

        // PageList 생성
        makePageList(result.getPageable());

    }

    public void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 + 1
        this.size = pageable.getPageSize();

        // todo temp end page (아직 개선의 여지가 있음..)
        int tempEnd = (int) (Math.ceil(page/10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

    }
}
