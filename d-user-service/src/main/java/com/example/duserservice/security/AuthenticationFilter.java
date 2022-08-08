package com.example.duserservice.security;

import com.example.duserservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    
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


    //성공한 인증
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info(((User) authResult.getPrincipal()).getUsername());

    }

}
