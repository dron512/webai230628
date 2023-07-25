package com.jpa.org.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    // 자동증가하는 컬럼 기본키가 되고...
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    // User, Admin, Manger,
    private String name;

    public Role(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles" ,fetch = FetchType.LAZY)
    private List<Member> members;

}




