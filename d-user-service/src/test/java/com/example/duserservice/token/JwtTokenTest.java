package com.example.duserservice.token;


import com.example.duserservice.vo.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class JwtTokenTest {

    private static final String SECRET_KEY = "secret_key";
    private static final Long EXPIRATION_TIME = 1000 * 60 * 60L; //60분


    @Test
    public void jwtTest() {

        UserDto userDto = new UserDto();
        userDto.setUserId("zo4870");
        userDto.setEncryptedPwd("zo4870@naver.com");
        Date now = new Date();

        String token = Jwts.builder()
                .setSubject(userDto.getUserId())  //아이디 설정
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))        //만료 시간
                .signWith(SignatureAlgorithm.HS512 , SECRET_KEY)
                .claim("email" , userDto.getEmail())
                .compact();

        System.out.println(token);

    }
}
