package com.teamntp.firstproject.course.entity;


import com.teamntp.firstproject.attendance.entity.Attendance;
import com.teamntp.firstproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseIdx;

    @Builder.Default // 임시 처리
    private Long courseClassification = 20001L; // 훈련과정분류 20001: 직업교육훈련

    @Builder.Default // 임시 처리
    private Long centerName = 10101L; // 센터명 10101: 대구남부여성새로일하기센터

    private String courseName; // 훈련과정명

    private LocalDate startDate; // 과정 시작일

    private LocalDate endDate; // 과정 종료일

    @OneToMany
    @JoinColumn(name = "course_idx")
    private final List<Attendance> list = new ArrayList<>();

}
