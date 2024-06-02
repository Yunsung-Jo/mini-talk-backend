package dev.yunsung.minitalk.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private final Key key;

    public JwtTokenUtil(@Value("${spring.jwt.secret}") String secret) {
        byte[] bytes = Decoders.BASE64.decode(secret);
        bytes = Arrays.copyOf(bytes, 32);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(String userId) {
        Claims claims = Jwts.claims().build();
        claims.put("userId", userId);
        long expireTime = 1000 * 60 * 60 * 24;
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(key)
                .compact();
    }

    public String getUserId(String token) {
        return getClaims(token).get("userId").toString();
    }

    public boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token).getPayload();
    }
}
