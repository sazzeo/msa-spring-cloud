package com.example.duserservice.controller;


import com.example.duserservice.service.UserService;
import com.example.duserservice.vo.RequestLogin;
import com.example.duserservice.vo.RequestUser;
import com.example.duserservice.vo.ResponseUser;
import com.example.duserservice.vo.UserDto;
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
@RequestMapping("/user-service")
public class UserApiController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final Environment env;

    //상태 체크 함수
    @GetMapping("/health_check")
    public String status() {
        return String.format("User Service on Port %s" , env.getProperty("local.server.port"));
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestUser requestUser) {

        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto , ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity findUsers() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.findUserByAll());
    }

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
