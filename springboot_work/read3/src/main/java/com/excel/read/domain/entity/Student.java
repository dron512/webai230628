/*
package com.excel.read.domain.entity;

import com.excel.read.domain.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@BatchSize(size = 1000)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BAN")
    private Long id;
    
    @Column(name = "NAME")
    private String name;

    // Student Entity를 StudentDto로 변경해 반환하는 메서드
    public StudentDto toDto() {
        return new StudentDto(id, name);
    }
}



*/
