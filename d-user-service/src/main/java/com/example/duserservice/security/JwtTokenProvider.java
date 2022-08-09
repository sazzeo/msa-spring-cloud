package com.example.duserservice.security;

import com.example.duserservice.vo.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@NoArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "secret_key";
    private static final Long EXPIRATION_TIME = 1000 * 60 * 60L; //60분


    public String getBearerToken(UserDto userDto) {

        Date now = new Date();

        String token = Jwts.builder()
                .setSubject(userDto.getUserId())  //아이디 설정
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))        //만료 시간
                .signWith(SignatureAlgorithm.HS512 , SECRET_KEY)
                .compact();

        return "Bearer " +token;

    }


}
