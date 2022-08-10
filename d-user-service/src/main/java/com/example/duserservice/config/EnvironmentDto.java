package com.example.duserservice.config;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EnvironmentDto {

    private String serverPort;
    private String localServerPort;
    private String tokenSecret;
    private String tokenExpirationTime;


    @Builder
    public EnvironmentDto(String serverPort, String localServerPort, String tokenSecret, String tokenExpirationTime) {
        this.serverPort = serverPort;
        this.localServerPort = localServerPort;
        this.tokenSecret = tokenSecret;
        this.tokenExpirationTime = tokenExpirationTime;
    }

}
