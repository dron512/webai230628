package com.teamntp.firstproject.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;

    private String title;

    private String content;

    private Long writerMno; // 작성자 index 번호(Member mno가 pk O)

    private String writerEmail; // 작성자의 이메일(Member email pk X)

    private String writerName; // 작성자의 이름

    private String category; // 게시판 카테고리, board

    private LocalDateTime regDate; // baseEntity
    private LocalDateTime modDate; // baseEntity

    private int replyCount; // 해당 게시글의 댓글 수

}
