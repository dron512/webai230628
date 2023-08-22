package com.teamntp.firstproject.attendance.entity;

import com.teamntp.firstproject.attendance.dto.AttendanceDTO;
import com.teamntp.firstproject.course.entity.Course;
import com.teamntp.firstproject.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//@ToString(exclude = {"course", "member"})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceIdx;

    private long memberIdx;
    private long courseIdx;
    private String name;


//    @ManyToOne(fetch = FetchType.LAZY) // 훈련과정
//    private Course course;
//
//    @ManyToOne(fetch = FetchType.LAZY) // 해당 훈련과정에 소속된 학생
//    private Member member;

    private LocalDate regDate;

    private int cperiod;

    private String status;

    // todo 일단 임시 처리
    public static Attendance toSaveEntity(AttendanceDTO attendanceDTO){
        Attendance attendance = Attendance.builder()
                .name(attendanceDTO.getName())
                .memberIdx(attendanceDTO.getMemberIdx())
                .courseIdx(attendanceDTO.getCourseIdx())
                .regDate(attendanceDTO.getRegDate())
                .cperiod(attendanceDTO.getCperiod())
                .status(attendanceDTO.getStatus())
                .build();
        return attendance;
    }


}
