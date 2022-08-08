package com.example.duserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//jpa에서는 repository를 @EnableJpaRepositories 어노테이션 설정으로
// JpaRepository를 상속받는 모든 클래스들은 자동으로 빈으로 등록된다.
//부트에서는 이 설정이 기본이므로 따로 아무것도 하지 않아도 빈이 등록된다.
public interface UserRepository extends JpaRepository<UserEntity , Long> {

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByEmail(String email);

}
