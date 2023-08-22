package com.teamntp.firstproject.repository;

import com.teamntp.firstproject.attendance.entity.Attendance;
import com.teamntp.firstproject.attendance.repository.AttendanceRepository;
import com.teamntp.firstproject.course.entity.Course;
import com.teamntp.firstproject.member.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class AttendanceRepositoryTest {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    public void insertAttendanceStatus() {
        // 출석 정보
        IntStream.rangeClosed(1,20).forEach(i->{
            // courseIdx
            Long courseIdx = 1L; // 1번 훈련과정

            // member
            Long memberIdx = (long)i; // 20명

            // attendance
            for(int j=0;j<7;j++) {
                Attendance attendance = Attendance.builder()
                        .courseIdx(courseIdx)
                        .memberIdx(memberIdx)
                        .regDate(LocalDate.now())
                        .cperiod(j)
                        .status("◯")
                        .build();

                attendanceRepository.save(attendance);

            }

        });
    }
}
