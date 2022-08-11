package com.example.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class GatewayController {
    private final Environment env;

    @GetMapping("health_check")
    public Map<String, String> healthCheck() {

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("secret_key", env.getProperty("token.secret_key"));
        responseMap.put("localServerPort", env.getProperty("local.server.port"));

        return responseMap;
    }
}
