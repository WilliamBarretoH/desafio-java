package br.com.desafio.restaurante.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {

    public InvalidJwtAuthenticationException(String exception) {
        super(exception);

    }
}
