package com.shop.shop.entity;

import com.shop.shop.constant.Role;
import com.shop.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //회원은 이메일 통해 유일하게 구분해야함으로 동일값없게 unique 속성지정
    @Column(unique = true)
    private String email;

    private String password;
    private String address;
    //자바의 enum타입으 엔티티속성으로 받을 수 있음
    //enum을 사용시 기본적으로 순서가 저장됨으로 enum의 순서가 바뀔시 문제가됨.
    //enumtype.string 옵션이용해 string으로 저장하기를 권장!!!!!
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    }



}



