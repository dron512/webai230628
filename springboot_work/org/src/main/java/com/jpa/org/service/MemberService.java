package com.jpa.org.service;

import com.jpa.org.entity.Member;
import com.jpa.org.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
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
