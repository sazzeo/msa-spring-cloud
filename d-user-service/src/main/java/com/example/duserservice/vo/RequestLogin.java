package com.example.duserservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class RequestLogin {

    @NotNull
    private String email;

    @NotNull
    private String password;
}


