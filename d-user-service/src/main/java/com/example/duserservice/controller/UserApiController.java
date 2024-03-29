package com.example.duserservice.controller;


import com.example.duserservice.config.EnvironmentDto;
import com.example.duserservice.service.UserService;
import com.example.duserservice.vo.RequestLogin;
import com.example.duserservice.vo.RequestUser;
import com.example.duserservice.vo.ResponseUser;
import com.example.duserservice.vo.UserDto;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserApiController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final Environment env;

    //상태 체크 함수
    @Timed(value = "users.status", longTask = true)
    @GetMapping("/health_check")
    public EnvironmentDto status() {

        return EnvironmentDto.builder()
                .localServerPort(env.getProperty("local.server.port"))
                .serverPort(env.getProperty("server.port"))
                .tokenSecret(env.getProperty("token.secret_key"))
                .tokenExpirationTime(env.getProperty("token.expiration_time"))
                .build();
    }

    @Timed(value = "users.welcome", longTask = true)
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome user-service";
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestUser requestUser) {

        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity findUsers() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findUserByAll());
    }


    //유저정보와 오더정보를 한번에 가져오기 (order : restTemplate 호출)
    @GetMapping("/users/{userId}")
    public ResponseEntity findUserByUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findUserByUserId(userId));
    }

    //login 기능 : token과 userId 반환
    @PostMapping("login")
    public ResponseEntity login(RequestLogin requestLogin) {

        return null;
    }


}
