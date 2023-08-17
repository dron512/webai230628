package com.teamntp.firstproject.board.entity;

import com.teamntp.firstproject.common.entity.BaseEntity;
import com.teamntp.firstproject.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; // Member의 PK를 참조

    private LocalDateTime delDate;


    // 게시글 수정은 제목과 내용만 수정 가능하도록 함
    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }


}
