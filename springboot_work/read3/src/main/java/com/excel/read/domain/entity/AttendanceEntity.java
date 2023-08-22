package com.excel.read.domain.entity;

import com.excel.read.domain.dto.AttendanceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "attendance_table")
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 5, nullable = true)
    private String name;
    
    private LocalDate regDate;
    private int cperiod;
    private String status;

    public static AttendanceEntity toSaveEntity(AttendanceDto attendanceDto){
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setName(attendanceDto.getName());
        attendanceEntity.setCperiod(attendanceDto.getCperiod());
        attendanceEntity.setStatus(attendanceDto.getStatus());
        attendanceEntity.setStatus(attendanceDto.getStatus());
        return attendanceEntity;
    }


}
