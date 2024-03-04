package com.masala.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRETS_KEY.getBytes());
    public String generrateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = populateAuthrities(authorities);
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",authentication.getName())
                .claim("authorities",roles)
                .signWith(secretKey)
                .compact();
        return jwt;
    }

    private String populateAuthrities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority: authorities) {

            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }

    public String getEmaiFromJwt(String jwt) {

        jwt = jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}
