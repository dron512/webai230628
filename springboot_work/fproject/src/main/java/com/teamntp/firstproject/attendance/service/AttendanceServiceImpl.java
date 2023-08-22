package com.teamntp.firstproject.attendance.service;

import com.teamntp.firstproject.attendance.dto.AttendanceDTO;
import com.teamntp.firstproject.attendance.entity.Attendance;
import com.teamntp.firstproject.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public void save(AttendanceDTO attendanceDTO) {
        Attendance attendance = Attendance.toSaveEntity(attendanceDTO);
        attendanceRepository.save(attendance);
    }
}
