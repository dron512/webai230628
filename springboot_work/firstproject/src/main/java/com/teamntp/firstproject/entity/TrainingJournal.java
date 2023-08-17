package com.teamntp.firstproject.entity;

import com.teamntp.firstproject.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TrainingJournal extends BaseEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_trainingjournal")
    private Course course_trainingjournal;

}
