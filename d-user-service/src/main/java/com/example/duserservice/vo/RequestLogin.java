package com.example.duserservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class RequestLogin {

    @Email
    @NotNull(message = "Email cannot be null")
    @Size(min=2 , message = "Email not be less than two characters")
    private String email;

    @NotNull
    @Size(min=2 , message = "password must be equals or grater than 8 characters")
    private String password;

}


