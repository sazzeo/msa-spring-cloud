package com.example.duserservice.service;

import com.example.duserservice.repository.UserEntity;
import com.example.duserservice.vo.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);

    UserDto findUserByUserId(String userId);

    List<UserDto> findUserByAll();
}
