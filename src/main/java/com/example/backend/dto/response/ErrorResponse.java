package com.example.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@JsonRootName("data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private int code;
    private String message;

    public static ErrorResponse bad(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
    }
}
