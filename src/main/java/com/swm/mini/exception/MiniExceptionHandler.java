package com.swm.mini.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class MiniExceptionHandler {
    @ExceptionHandler(MiniException.class) // DMakerException 관련 에러
    public MiniErrorResponse handleException (
            MiniException e,
            HttpServletRequest request){
        log.error("errorCode: {}, url: {}, message: {}",
                e.getMiniErrorCode(), request.getRequestURI(), e.getDetailMessage());
        return MiniErrorResponse.builder()
                .errorCode(e.getMiniErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }
}
