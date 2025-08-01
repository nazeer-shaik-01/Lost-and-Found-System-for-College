package com.college.lostandfoundapi.security.dto;

import com.college.lostandfoundapi.security.Role;

public class TokenResponse {

    private String token;
    private Role role;

    public TokenResponse(String token, Role role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}