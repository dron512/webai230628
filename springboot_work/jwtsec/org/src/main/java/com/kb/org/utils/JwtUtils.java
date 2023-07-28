package com.kb.org.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    // 이게 토큰 비번 입니다.
    private String key = "asdfasdfasdfasfasdf";

    // 사용자명을 가지고..... jwt 토큰 생성함..
    public String createToken(String username, Long expired){
        // hashmap -> 키 value
        Claims claims = Jwts.claims();
        claims.put("username",username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expired))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
