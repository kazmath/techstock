package br.com.techhub.techstock.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @SuppressWarnings("deprecation")
    public String generateToken(UserSS user) {
        Claims claims = Jwts.claims().subject(user.getUsername()).build();

        claims.put("id", user.getId());

        return Jwts.builder()
            .claims(claims)
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey(), SignatureAlgorithm.ES512) // TODO: Tirar deprecated
            .compact();
    }

    public boolean tokenValid(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            var username = claims.getSubject();
            var expirationDate = claims.getExpiration();
            var date = new Date(System.currentTimeMillis());
            return username != null && expirationDate != null && date.before(
                expirationDate
            );
        }

        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody(); // TODO: Tirar deprecated
        } catch (Exception e) {
            return null;
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }

        return null;
    }

}
