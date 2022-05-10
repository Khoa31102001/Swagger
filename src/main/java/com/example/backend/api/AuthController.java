package com.example.backend.api;

import com.example.backend.dto.response.DataResponse;
import com.example.backend.dto.resquest.LoginRequestDto;
import com.example.backend.dto.resquest.RegisterRequestDto;
import com.example.backend.model.Account;
import com.example.backend.service.AccountService;
import com.example.backend.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    public DataResponse<String> login(@Valid @RequestBody LoginRequestDto request) {
        return DataResponse.success(securityService.login(request));
    }
//
//    @PostMapping("/sign-up")
//    public DataResponse<String> registerAccount(@Valid @RequestBody RegisterRequestDto request) {
//        accountService.registerAccount(request);
//        return DataResponse.success();
//    }

}
