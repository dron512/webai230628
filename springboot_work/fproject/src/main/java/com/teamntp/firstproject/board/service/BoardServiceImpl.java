package com.teamntp.firstproject.board.service;

import com.teamntp.firstproject.board.dto.BoardDTO;
import com.teamntp.firstproject.common.dto.PageRequestDTO;
import com.teamntp.firstproject.common.dto.PageResultDTO;
import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.member.entity.Member;
import com.teamntp.firstproject.board.repository.BoardRepository;
import com.teamntp.firstproject.board.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository; // 자동 주입 final
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        log.info(boardDTO);

        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO(
                (Board) en[0]
                , (Member) en[1]
                , (Long) en[2]));

        /* 쿼리메소드, @Query 로 처리할 시
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending()));*/

        // Querydsl 로 처리할 시
        Page<Object[]> result = boardRepository.searchPage(pageRequestDTO.getType()
                , pageRequestDTO.getKeyword()
                , pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);

    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        // 1. 댓글 삭제
        replyRepository.deleteByBno(bno);
        // 2. 게시글 삭제
        boardRepository.deleteById(bno);
        // 1과 2는 트랜잭션으로 처리되어야 함

    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.getReferenceById(boardDTO.getBno());
        if (board != null) {

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            boardRepository.save(board);

        }
    }
}
