package com.college.lostandfoundapi.security;

import com.college.lostandfoundapi.security.dto.LoginRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String USER_USERNAME = "user";
    private static final String USER_PASSWORD = "user123";

    public Role login(LoginRequest request) {
        if (ADMIN_USERNAME.equals(request.getUsername()) && ADMIN_PASSWORD.equals(request.getPassword())) {
            return Role.ROLE_ADMIN;
        } else if (USER_USERNAME.equals(request.getUsername()) && USER_PASSWORD.equals(request.getPassword())) {
            return Role.ROLE_USER;
        }
        // For simplicity, we'll throw a runtime exception for invalid credentials
        throw new RuntimeException("Invalid username or password");
    }
}