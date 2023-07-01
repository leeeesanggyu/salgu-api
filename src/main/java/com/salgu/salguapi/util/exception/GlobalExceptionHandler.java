package com.salgu.salguapi.util.exception;

import com.salgu.salguapi.util.response.ResponseCodeEnum;
import com.salgu.salguapi.util.response.ResponseWithData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
    })
    public ResponseWithData customException(CustomException e) {
        log.info("", e.getMessage());
        return ResponseWithData.failed(e.getResponseCodeEnum()).message(e.getMessage());
    }

    @ExceptionHandler({
            Exception.class
    })
    public ResponseWithData AllException(Exception e) {
        log.error("not handling error.", e);
        return ResponseWithData.failed(ResponseCodeEnum.FAILED).message(e.getMessage());
    }

}
