package com.pureamorous.spring_rbac_jwt.controller;

import com.pureamorous.spring_rbac_jwt.controller.customization.JwtHandler;
import com.pureamorous.spring_rbac_jwt.controller.customization.UserAuthenticationInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/auth",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtHandler jwtHandler;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@Valid @RequestBody UserAuthenticationInfo info){
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword())
        );


        var authorities = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);

        return jwtHandler.generate(info.getUsername(), authorities);
    }
}
