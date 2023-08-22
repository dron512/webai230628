package com.teamntp.firstproject.attendance.repository;

import com.teamntp.firstproject.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
