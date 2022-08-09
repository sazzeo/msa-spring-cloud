package com.example.gateway.security;

import com.example.gateway.vo.UserDto;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenParser {

    private static final String SECRET_KEY = "secret_key";


    public String getTokenSubject(String token) {

        String subject = Jwts.parser().parseClaimsJws(token).getBody().getSubject();
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
            String subject = Jwts.parser().parseClaimsJws(validBearerToken).getBody().getSubject();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getToken(String validBearerToken) {
        return validBearerToken.split(" ")[1];
    }
}
