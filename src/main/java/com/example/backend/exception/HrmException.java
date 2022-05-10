package com.example.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class HrmException extends RuntimeException {
    private final String message;
    public static final String USERNAME_EXISTS = "Username already exists";
    public static final String INCORRECT_PASSWORD = "Wrong password";
    public static final String NOT_PASSWORD_MATCH = "Password and confirm password do not match";
    public static final String USER_NOT_FOUND = "User not found";
}
