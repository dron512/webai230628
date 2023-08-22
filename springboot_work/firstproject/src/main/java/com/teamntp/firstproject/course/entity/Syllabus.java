package com.teamntp.firstproject.course.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "course")
public class Syllabus { // 강의계획서 파일에 대한 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snum; // 강의계획서 idx

    private String uuid; // 고유 번호 생성

    private String syllabusFileName; // 강의계획서 파일이름

    private String syllabusFilePath; // 강의계획서 파일경로(년/월/일)

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    // 강의계획서가 변경되어 또 업로드 할 수도 있음
    // 실제로 우리 반은 3번 정도 변경됨
    // 다양한..확장자로.. 제공할 수도 있다..
    // 사용자를 배려할 수록 코딩이 힘들어진다..
}
