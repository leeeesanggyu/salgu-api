package com.salgu.salguapi.user.controller;

import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/verification/{email}")
    public ResponseWithData verification(
            @PathVariable String email
    ) {
        return ResponseWithData.success("검증 완료");
    }
}
