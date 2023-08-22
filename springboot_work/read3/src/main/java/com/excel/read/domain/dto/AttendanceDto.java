package com.excel.read.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private int id;
    // member_idx ...
    private long member_idx;
    private String name;
    private LocalDate regDate;
    private int cperiod;
    private String status;

    public AttendanceDto(long member_idx,int id, String name,LocalDate regDate) {
        this.member_idx = member_idx;
        this.id = id;
        this.name = name;
        this.regDate = regDate;
    }
}
