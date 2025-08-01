package com.college.lostandfoundapi.security;

import com.college.lostandfoundapi.security.dto.LoginRequest;
import com.college.lostandfoundapi.security.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        try {
            Role role = authService.login(request);
            String token = tokenService.generateToken(role);
            TokenResponse response = new TokenResponse(token, role);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}