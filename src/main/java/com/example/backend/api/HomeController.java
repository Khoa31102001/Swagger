package com.example.backend.api;

import com.example.backend.dto.response.DataResponse;
import com.example.backend.dto.resquest.ChangePasswordRequestDto;
import com.example.backend.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/home")
public class HomeController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/change-password")
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @ApiOperation("CHANGE PASSWORD")
    public DataResponse<String> changePassword(@Valid @RequestBody ChangePasswordRequestDto request) {
        accountService.changePassword(request);
        return DataResponse.success();
    }
}
