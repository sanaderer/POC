package com.github.sanaderer.POC.exceptions;

import com.github.sanaderer.POC.controllers.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MainAddressException.class)
    public ResponseEntity<ErrorResponse> mainAddressException(MainAddressException exception, WebRequest request) {
        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.CONFLICT.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception, WebRequest request) {
        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ErrorResponse> cepNotFoundException(CepNotFoundException exception, WebRequest request) {
        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
