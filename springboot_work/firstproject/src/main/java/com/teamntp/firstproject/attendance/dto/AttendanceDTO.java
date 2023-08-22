package com.teamntp.firstproject.attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
    private int id;
    // member_idx ...
    private long member_idx;
    private String name;
    private LocalDate regDate;
    private int cperiod;
    private String status;
    private long course_idx;

    // todo 생성자 정리
    public AttendanceDTO (long member_idx,int id, String name,int cperiod, LocalDate regDate) {
        this.cperiod = cperiod;
        this.member_idx = member_idx;
        this.id = id;
        this.name = name;
        this.regDate = regDate;
    }

    public AttendanceDTO(long member_idx, int id, String name) {
        this.member_idx = member_idx;
        this.id = id;
        this.name = name;
    }
}
