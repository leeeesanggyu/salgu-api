package com.salgu.salguapi;

import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
        HelloDto helloDto = null;
        try {
            helloDto = HelloDto.builder()
                    .message(message)
                    .dbUrl(dbUrl)
                    .version("v4")
                    .hostName(InetAddress.getLocalHost().getHostName())
                    .ip(InetAddress.getLocalHost().getHostAddress())
                    .build();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        log.info("HelloController.hello() => {}", helloDto);
        return ResponseWithData.success(helloDto);
    }

    @Data
    @Builder
    public static class HelloDto {
        private String message;
        private String dbUrl;
        private String hostName;
        private String ip;
        private String version;
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
