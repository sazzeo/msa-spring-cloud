package com.example.duserservice.service;

import com.example.duserservice.repository.UserEntity;
import com.example.duserservice.repository.UserRepository;
import com.example.duserservice.vo.ResponseOrder;
import com.example.duserservice.vo.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    //@Lazy : 순환참조 에러로 @Lazy 추가
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           @Lazy PasswordEncoder passwordEncoder ,
                           RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
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

        String orderUrl = String.format("http://ORDER-SERVICE/%s/orders" , userId);  //@LoadBalancer 사용으로 주소 바꿈
        // ParameterizedTypeReference  :Generic 타입을 class로 넘겨줄 때 사용하는 객체
        List<ResponseOrder> orderList = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ResponseOrder>>(){})
                .getBody();

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
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없음")));
    }
}
