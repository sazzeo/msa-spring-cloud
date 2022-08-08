package com.example.duserservice.service;

import com.example.duserservice.repository.UserEntity;
import com.example.duserservice.repository.UserRepository;
import com.example.duserservice.vo.ResponseOrder;
import com.example.duserservice.vo.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //@Lazy : 순환참조 에러로 @Lazy 추가
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    //UserDetailService Override
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없음."));

        //이후 password 인증 절차를 위해 db에서 꺼낸 password 정보를 User에다 담아서 넘김
        return new User(userEntity.getEmail(), userEntity.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);

        return userDto;
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() ->
                new UsernameNotFoundException("유저를 찾을 수 없음")
        );

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public List<UserDto> findUserByAll() {

        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());

    }


}
