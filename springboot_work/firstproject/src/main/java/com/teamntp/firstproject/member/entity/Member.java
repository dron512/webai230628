package com.teamntp.firstproject.member.entity;

import com.teamntp.firstproject.attendance.entity.Attendance;
import com.teamntp.firstproject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx; // pk index

    @Column(unique = true)
    private String loginId; // 자체회원: 회원이 설정한 아이디, 구글회원: google-sub

    private String password; // 비밀번호

    // 가입일, 수정일은 BaseEntity 처리

    private LocalDate withdrawalDate; // 탈퇴일

    // ***Google Login 추가
    private String path; // form, google

    private String type; // student, teacher

    @Builder.Default // 임시 처리
    private Long accountStatus = 30002L; // 30002(승인)으로 처리

    // 권한 컬럼
    // 임시 선생님 회원가입
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private final Set<MemberRole> roleSet = new HashSet<>(); // 권한 컬럼

    private String name; // 이름

    private String mobileNo; // 연락처

    private String email; // 이메일

    private Long zipcode; // 우편번호

    private String address; // 주소

    private String detailedAddress;// 상세주소

    @OneToMany
    @JoinColumn(name = "member_idx")
    private final List<Attendance> list = new ArrayList<>();

    // Member 권한 추가 method
    public Set<MemberRole> addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
        return roleSet;
    }

    public void pw(String password){
        this.password = password;
    }

}
