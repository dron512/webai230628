package com.teamntp.firstproject.entity;

import com.teamntp.firstproject.common.entity.BaseEntity;
import com.teamntp.firstproject.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course extends BaseEntity {
    @Id
    private Long id;

    private String bunru;

    private String centerName;

    private LocalDate startDate;
    private LocalDate endDate;

    private String filePath;
    private String fileName;

    @OneToMany(mappedBy = "course_attendance")
    private List<Attendance> course_attendances  = new ArrayList<>();

    @OneToMany(mappedBy = "course_trainingjournal")
    private List<TrainingJournal> course_trainingjournals = new ArrayList<>();


}
