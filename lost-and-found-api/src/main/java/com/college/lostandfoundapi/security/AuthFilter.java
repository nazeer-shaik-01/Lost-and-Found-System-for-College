package com.college.lostandfoundapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

// Remove the @Component annotation from here
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Optional<Role> role = tokenService.getRoleFromToken(token);

            if (role.isPresent()) {
                request.setAttribute("userRole", role.get());
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
}