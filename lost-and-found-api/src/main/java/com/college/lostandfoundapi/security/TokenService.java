package com.college.lostandfoundapi.security;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    private final Map<String, Role> tokenStore = new HashMap<>();

    public String generateToken(Role role) {
        String token = UUID.randomUUID().toString();
        tokenStore.put(token, role);
        return token;
    }

    public Optional<Role> getRoleFromToken(String token) {
        return Optional.ofNullable(tokenStore.get(token));
    }
}