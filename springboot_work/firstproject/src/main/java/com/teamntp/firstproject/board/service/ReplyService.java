package com.teamntp.firstproject.board.service;

import com.teamntp.firstproject.board.dto.ReplyDTO;
import com.teamntp.firstproject.board.entity.Board;
import com.teamntp.firstproject.board.entity.Reply;

import java.util.List;

public interface ReplyService {
    // 댓글 등록
    Long register(ReplyDTO replyDTO);

    // 특정 게시물의 댓글 목록
    List<ReplyDTO> getList(long bno);

    // 댓글 수정
    void modify(ReplyDTO replyDTO);

    // 댓글 삭제
    void remove(long rno);

    // ReplyDTO 를 Reply 로 변환, Board 객체의 처리를 해줘야 함
    default Reply dtoToEntity(ReplyDTO replyDTO) {

        return Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(Board.builder()
                        .bno(replyDTO.getBno())
                        .build())
                .build();

    }

    // Reply 를 ReplyDTO 로 변환, Board 객체가 필요하지 않음. 게시물 번호만 있으면 됨
    default ReplyDTO entityToDto(Reply reply) {
        return ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegdate())
                .modDate(reply.getModdate())
                .build();
    }

}
