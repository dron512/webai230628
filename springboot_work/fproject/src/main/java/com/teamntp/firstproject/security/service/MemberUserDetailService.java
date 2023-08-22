package com.teamntp.firstproject.security.service;

import com.teamntp.firstproject.member.entity.Member;
import com.teamntp.firstproject.member.repository.MemberRepository;
import com.teamntp.firstproject.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("MemberUserDetailService loadUserByUsername " + username);

        // 회원 정보가 있는지 조회(자체회원: form)
        Optional<Member> result = memberRepository.findByLoginId(username, "form");

        // 없으면 throw exception
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Check Your LoginId");
        }

        // 회원 정보를 가져옴
        Member member = result.get();

        log.info("--------------------------------------------");
        log.info(member);

        // AuthMemberDTO 로 변환
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getLoginId()
                , member.getPassword()
                , member.getPath()
                , member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority
                ("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setName(member.getName());
        authMemberDTO.setPath(member.getPath());

        return authMemberDTO;
    }
}
