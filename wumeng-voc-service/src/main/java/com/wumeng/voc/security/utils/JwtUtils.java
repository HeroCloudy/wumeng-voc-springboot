package com.wumeng.voc.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    public static final String SECRET = "YYGCODER_ABCDEFGHUJKLMNOPQRSTUVWXYZ1234567890";

    public static String createToken(String subject, long expire) {
        System.out.println(expire);
        Date current = Date.from(Instant.now());
        Date expireDate = Date.from(Instant.now().plusSeconds(expire));

        System.out.println(new Date().getTime());
        System.out.println(current.getTime());
        System.out.println(expireDate.getTime());

        return Jwts.builder()
                .header()
                .add("type", "JWT")
//                .add("alg", "HS256")
                .and()
                .id(UUID.randomUUID().toString())
                .issuer("YYG")
                .issuedAt(current)
//                .expiration(new Date(System.currentTimeMillis() + expire))
                .expiration(expireDate)
                .subject(subject)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
