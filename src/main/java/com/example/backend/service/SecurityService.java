package com.example.backend.service;

import com.example.backend.config.provider.JwtTokenProvider;
import com.example.backend.config.service.AccountDetail;
import com.example.backend.dto.resquest.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public String login(LoginRequestDto request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
                );
        return tokenProvider.generatedJwt(((AccountDetail) authentication.getPrincipal()));
    }

}
