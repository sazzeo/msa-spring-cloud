package com.example.gateway.security;

import com.example.gateway.vo.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenParser {

    private static final String SECRET_KEY = "secret_key";


    public String getTokenSubject(String token) {

        String subject = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return subject;

    }

    public boolean isBearerToken(String bearerToken) {
        String[] tokenInfo = bearerToken.split(" ");
        if (!tokenInfo[0].equals("Bearer") || tokenInfo.length != 2) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isValidToken(String validBearerToken) {

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(validBearerToken);
            return true;
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
    }

    public String getToken(String validBearerToken) {
        return validBearerToken.split(" ")[1];
    }
}
