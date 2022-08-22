package com.example.duserservice.service;

import com.example.duserservice.client.OrderServiceClient;
import com.example.duserservice.repository.UserEntity;
import com.example.duserservice.repository.UserRepository;
import com.example.duserservice.vo.ResponseOrder;
import com.example.duserservice.vo.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final OrderServiceClient orderServiceClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    //@Lazy : 순환참조 에러로 @Lazy 추가
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           @Lazy PasswordEncoder passwordEncoder,
                           RestTemplate restTemplate,
                           OrderServiceClient orderServiceClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
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
        String id = userDto.getEmail().split("@")[0];
        userDto.setUserId(id);

        userDto.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);

        return userDto;
    }


    //userId 찾고 order 정보도 함께 호출하기 =>restTemplate 이용
    @Override
    public UserDto findUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(() ->
                new UsernameNotFoundException("유저를 찾을 수 없음")
        );

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        log.info("Before call orders microservice");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitBreaker");  //서킷 브레이커 이름

        List<ResponseOrder> orderList = circuitBreaker.run(()-> orderServiceClient.getOrders(userId) ,
                throwable -> new ArrayList<>());

        log.info("After call orders microservice");

        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public List<UserDto> findUserByAll() {

        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());

    }

    @Override
    public UserDto findUserByEmail(String email) {
        return new UserDto(userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없음")));
    }
}
