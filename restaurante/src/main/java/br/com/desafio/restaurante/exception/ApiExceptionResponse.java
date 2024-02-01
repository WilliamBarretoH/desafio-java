package br.com.desafio.restaurante.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class ApiExceptionResponse {

    private HttpStatus status;
    private List<String> errors;

    public ApiExceptionResponse(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiExceptionResponse(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Collections.singletonList(error);
    }
}