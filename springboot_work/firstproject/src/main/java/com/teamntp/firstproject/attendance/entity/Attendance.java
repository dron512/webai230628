package com.teamntp.firstproject.attendance.entity;

import com.teamntp.firstproject.attendance.dto.AttendanceDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceIdx;

    private String name;
    private LocalDate regDate;

    private int cperiod;
//    private long member_idx;
    private String status;

    // todo 일단 임시 처리
    public static Attendance toEntity(AttendanceDTO attendanceDTO){
        Attendance attendance = Attendance.builder()
                .name(attendanceDTO.getName())
                .regDate(attendanceDTO.getRegDate())
                .cperiod(attendanceDTO.getCperiod())
                .status(attendanceDTO.getStatus())
                .build();
        return attendance;
    }


}
