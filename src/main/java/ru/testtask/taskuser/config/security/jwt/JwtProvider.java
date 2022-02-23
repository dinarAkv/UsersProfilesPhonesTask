package ru.testtask.taskuser.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${ru.testtask.taskuser.jwt.secret}")
    private String secret;

    public String generateToken(String login) {
        java.util.Date tokenExpirationTime = Date.from(LocalDateTime.now()
                .plusHours(2).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(tokenExpirationTime)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("During validation exception occur. Token invalid.", e);
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}
