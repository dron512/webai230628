package com.teamntp.firstproject.entity;

import com.teamntp.firstproject.common.entity.BaseEntity;
import com.teamntp.firstproject.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Attendance extends BaseEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_attendance")
    private Course course_attendance;


}
