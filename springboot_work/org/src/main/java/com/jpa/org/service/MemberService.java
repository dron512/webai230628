package com.jpa.org.service;

import com.jpa.org.entity.Member;
import com.jpa.org.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("일로 오나"+username);
        Member member = memberRepository.findByUsername(username);
        System.out.println(member);
        if( member == null){
            throw new UsernameNotFoundException("그런 회원 없다.");
        }
        return User.builder()
                .username(username)
                .password(member.getPassword())
                .roles(new String[]{"USER",})
                .build();
    }

    public void save(Member member) {
        boolean result = validate(member);
        if(result) {
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            memberRepository.save(member);
        }
    }

    public boolean validate(Member member){
        Member dbmember =
                memberRepository.findByUsername(member.getUsername());
        if(dbmember == null)
            return true;
        else
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }
}
