package br.com.desafio.restaurante.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private final static String UNEXPECTED_ERROR = "UNEXPECTED ERROR";
    private final static String NOT_FOUND_ERROR = "NOT FOUND ERROR";


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> exceptionHandler(final Exception exception) {

        log.error("m=exceptionHandler ex={}, cause={}", exception.getMessage(),
                exception.getCause() != null ? exception.getCause().getMessage() : null);
        return status(INTERNAL_SERVER_ERROR).body(new ApiExceptionResponse(INTERNAL_SERVER_ERROR, ""));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(OrderEmptyException.class)
    public ResponseEntity<Object> handleEmptyOrder(OrderEmptyException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(UpdateOrderWithEmptyListException.class)
    public ResponseEntity<Object> handleUpdateOrderWithEmptyListException(UpdateOrderWithEmptyListException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(ProductWithEptyNameException.class)
    public ResponseEntity<Object> handleProductWithEptyNameException(ProductWithEptyNameException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(BAD_REQUEST, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(PriceNotAuthorizedException.class)
    public ResponseEntity<Object> handlePriceNotAuthorizedException(PriceNotAuthorizedException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(NOT_ACCEPTABLE, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(NOT_FOUND, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<Object> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException exception) {

        ApiExceptionResponse apiErrorMessage = new ApiExceptionResponse(FORBIDDEN, exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }
}
