package com.example.backend.exception;

import com.example.backend.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(value = {HrmException.class})
    public ResponseEntity<?> hrmException(Exception e) {
        return ResponseEntity.ok(ErrorResponse.bad(e.getMessage()));
    }


}
