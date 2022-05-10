package com.example.backend.dto.resquest;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequestDto {
    @NotNull(message = "Không được")
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
