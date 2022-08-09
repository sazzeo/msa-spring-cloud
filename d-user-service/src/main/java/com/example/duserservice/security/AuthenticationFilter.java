package com.example.duserservice.security;

import com.example.duserservice.service.UserService;
import com.example.duserservice.vo.RequestLogin;
import com.example.duserservice.vo.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


@Component
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //메소드 통과 순서 : attemptAuthentication() => UserServiceImpl => User(객체에 저장) => 성공시 => successfulAuthentication()

    private final UserService userService;
    private final Environment env;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthenticationFilter(@Lazy AuthenticationManager authenticationManager, UserService userService, Environment env, JwtTokenProvider jwtTokenProvider) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;                                                     
        this.env = env;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // attempt authentication : 인증 시도
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        //post 형태로 전달되는 데이터는 request 파라미터로 받을 수 없음
        try {
            System.out.println("여기:: " + request.getInputStream());
            ObjectMapper objectMapper = new ObjectMapper();
            //서블릿에서 post요청시 body는 getParameter로 읽을 수 없고 get InputStream으로 직접 읽어야함.
            RequestLogin creds = objectMapper.readValue(request.getInputStream() ,  RequestLogin.class);

            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail() , creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

                                                             
    //성공한 인증                        => 토큰 발행해서 헤더로 설정   => 이후 클라이언트는 헤더로 토큰을 보내며, 이 검증 필터는 gateway filter에 구축함.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String email = ((User)authResult.getPrincipal()).getUsername();
       // log.info(((User) authResult.getPrincipal()).getUsername());
        UserDto userDto = userService.findUserByEmail(email);

        String token = jwtTokenProvider.getBearerToken(userDto);
        response.addHeader("Authorization" , token);
        response.addHeader("userId" , userDto.getUserId());

    }

}
