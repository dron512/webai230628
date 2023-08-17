package com.teamntp.firstproject.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
    // 시큐리티 처리에 필요한 멤버 변수
    private String loginId;

    private String password;

    private String name;

    private boolean fromSocial;

    // 추가
    private Map<String, Object> attr;

    // User 생성자
    public AuthMemberDTO(
            String username
            , String password
            , boolean fromSocial
            , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.loginId = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }

    // OAuth2User 생성자
    public AuthMemberDTO(
            String username
            , String password
            , boolean fromSocial
            , Collection<? extends GrantedAuthority> authorities
    , Map<String,Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
