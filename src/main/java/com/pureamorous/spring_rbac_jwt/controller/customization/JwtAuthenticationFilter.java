package com.pureamorous.spring_rbac_jwt.controller.customization;


import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHandler jwtHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        doBefore(request,response);
        filterChain.doFilter(request,response);
    }

    private void doBefore(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer "))
            return;

        String token = authHeader.replace("Bearer ", "");

        try {
            var decoded = jwtHandler.verify(token);
            var auth = UsernamePasswordAuthenticationToken.authenticated(
                    decoded.getSubject(),
                    null,
                    AuthorityUtils.createAuthorityList(decoded.getClaim("authorities").asList(String.class))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }catch (JWTVerificationException jwtVerificationException) {
            throw new ServletException("Token Not verified", jwtVerificationException);
        }
    }
}
