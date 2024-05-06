package com.pureamorous.spring_rbac_jwt.controller.customization;

import org.springframework.security.core.AuthenticationException;

public class TokenVerificationException extends AuthenticationException {
    public TokenVerificationException(String msg) {
        super(msg);
    }

}
