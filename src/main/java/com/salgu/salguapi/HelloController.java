package com.salgu.salguapi;

import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Value("${profile.message}")
    private String message;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    private Boolean applicationStatus = true;

    @GetMapping("/hello")
    public ResponseWithData hello() {
        log.info("HelloController.hello(), {}, {}", message, dbUrl);
        return ResponseWithData.success(message);
    }

    @GetMapping("/status")
    public ResponseEntity getStatus() {
        log.info("kubernetes liveness probe healthCheck call");
        if (applicationStatus == false) {
            return new ResponseEntity<>("now => " + applicationStatus, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("now => " + applicationStatus, HttpStatus.OK);
    }

    @PatchMapping("/status")
    public ResponseEntity changeStatus() {
        if (applicationStatus == true) {
            applicationStatus = false;
        } else if (applicationStatus == false) {
            applicationStatus = true;
        }

        return new ResponseEntity<>("now => " + applicationStatus, HttpStatus.OK);
    }
}
