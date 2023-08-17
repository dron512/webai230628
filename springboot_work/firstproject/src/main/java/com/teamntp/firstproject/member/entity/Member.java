package com.teamntp.firstproject.member.entity;

import com.teamntp.firstproject.common.entity.BaseEntity;
import com.teamntp.firstproject.entity.Attendance;
import com.teamntp.firstproject.entity.Course;
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
    // Member 공통 부분 처리
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberIdx; // pk index

    @Column(unique = true)
    private String loginId; // 자체 회원의 경우 회원이 설정한 아이디(예: kwontory), 소셜 회원의 경우 sub값으로 처리

    private String password; // 비밀번호

    // 가입일, 수정일은 BaseEntity 처리

    private LocalDate withdrawalDate; // 탈퇴일

    // todo 하는 중
    private Long type; // 1: 자체 회원, 2: Google 회원

    @Builder.Default // 임시 처리
    private Long accountStatus = 30002L; // 30002(승인)으로 처리

    // 권한 컬럼
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private final Set<MemberRole> roleSet = new HashSet<>(); // 권한 컬럼

    private String name; // 이름

    private LocalDate birthDate; // 생년월일

    private String sex; // 성별(F:여성, M:남성)

    private String mobileNo; // 연락처

    private String email; // 이메일

    private Long zipcode; // 우편번호

    private String address; // 주소

    private String detailedAddress;// 상세주소

    private boolean fromSocial; // todo 임시

    // Member 권한 추가 method
    public Set<MemberRole> addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
        return roleSet;
    }

    @OneToMany
    @JoinColumn(name = "member_idx")
    private List<Attendance> member_attendances = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "member_idx")
    private List<Course> member_courses = new ArrayList<>();

    //@OneToMany(mappedBy = "member_attendances")
    //private List<Course> member_attendances = new ArrayList<>();
}
