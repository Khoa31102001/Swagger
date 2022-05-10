package com.example.backend.config.provider;

import com.example.backend.config.service.AccountDetail;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@Service
public class JwtTokenProvider implements Serializable {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String encodePassword = "HRM";

    public String generatedJwt(AccountDetail accountDetail) {
        Date date = new Date();
        String encodeSecret = TextCodec.BASE64.encode("HRM");
        return Jwts.builder()
                .setSubject(accountDetail.getUsername())
                .claim("roles", accountDetail.getAuthorities())
                //.claim("name", accountDetail.getName())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 604800000L))
                .signWith(SignatureAlgorithm.HS512, encodeSecret)
                .compact();
    }

    public String getUsername(String token) {
        String encodeSecret = TextCodec.BASE64.encode(encodePassword);
        return Jwts.parser()
                .setSigningKey(encodeSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            String encodeSecret = TextCodec.BASE64.encode(encodePassword);
            Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX + " ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
