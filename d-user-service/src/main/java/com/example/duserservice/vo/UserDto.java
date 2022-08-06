package com.example.duserservice.vo;

import com.example.duserservice.repository.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;

    public UserDto(UserEntity userEntity) {
        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.encryptedPwd = userEntity.getEncryptedPwd();
        this.userId = userEntity.getUserId();
    }
}
