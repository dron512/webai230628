package com.jpa.org.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    private String username;
    private String password;

    private String email;
    private String age;
    private String gender;

    @ManyToMany
    @JoinTable(name="member_role",
          joinColumns = @JoinColumn(name = "member_idx"),
            inverseJoinColumns = @JoinColumn(name = "role_idx"))
    private List<Role> roles;
}
