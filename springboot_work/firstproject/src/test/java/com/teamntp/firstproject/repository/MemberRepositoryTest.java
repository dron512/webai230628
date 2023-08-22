package com.teamntp.firstproject.repository;

import com.teamntp.firstproject.member.entity.Member;
import com.teamntp.firstproject.member.entity.MemberRole;
import com.teamntp.firstproject.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Test
    public void insertMember() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .loginId("user"+i)
                    .password(passwordEncoder.encode("1111"))
                    .name("tory"+i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Transactional
    @Test
    public void tempAccount() {
        /*Optional<Member> result = memberRepository.findById(1L);
        Member member = result.get();
        member.addMemberRole(MemberRole.TEACHER);
        System.out.println(member.getRoleSet());
        memberRepository.save(member);*/

        Member member = Member.builder()
                .loginId("kwontory")
                .password(passwordEncoder.encode("1111"))
                .name("jihyeon")
                .build();
        member.addMemberRole(MemberRole.TEACHER);
        memberRepository.save(member);


    }
}
