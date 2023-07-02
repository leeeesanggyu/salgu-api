package com.salgu.salguapi;

import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Value("${profile.message}")
    private String message;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @GetMapping("/hello")
    public ResponseWithData hello() {
        log.info("HelloController.hello(), {}, {}", message, dbUrl);
        return ResponseWithData.success(message);
    }
}
