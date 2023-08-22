package com.excel.read.service;

import com.excel.read.domain.dto.AttendanceDto;
import com.excel.read.domain.entity.AttendanceEntity;
import com.excel.read.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public void save(AttendanceDto attendanceDto) {
        AttendanceEntity attendanceEntity = AttendanceEntity.toSaveEntity(attendanceDto);
        attendanceRepository.save(attendanceEntity);
    }
}
