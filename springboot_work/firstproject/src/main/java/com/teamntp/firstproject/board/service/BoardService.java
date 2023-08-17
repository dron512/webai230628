package com.teamntp.firstproject.board.service;

import com.teamntp.firstproject.board.dto.BoardDTO;
import com.teamntp.firstproject.board.dto.PageRequestDTO;
import com.teamntp.firstproject.board.dto.PageResultDTO;
import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.member.entity.Member;

public interface BoardService {
    Long register(BoardDTO boardDTO);

    // 목록 처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void modify(BoardDTO boardDTO);

    void removeWithReplies(Long bno); // 게시글 번호로 댓글 삭제

    default Board dtoToEntity(BoardDTO boardDTO) {
        Member member = Member.builder()
                .memberIdx(boardDTO.getWriterMno())
                .build();

        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(member)
                .build();

        return board;
    }

    // Object[] Board, Member, replyCount -> BoardDTO
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
            BoardDTO boardDTO = BoardDTO.builder()
                    .bno(board.getBno())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .regDate(board.getRegdate())
                    .modDate(board.getModdate())
                    .writerMno(member.getMemberIdx())
                    .writerEmail(member.getEmail())
                    .writerName(member.getName())
                    .replyCount(replyCount.intValue()) // long으로 나오므로 int로 처리하도록 함
                    .build();
            return boardDTO;
    }
}
