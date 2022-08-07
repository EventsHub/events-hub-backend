package com.saxakiil.eventshubbackend.handler;

import com.saxakiil.eventshubbackend.dto.error.FieldError;
import com.saxakiil.eventshubbackend.dto.error.ValidateFieldsError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        return new ResponseEntity<>(new ValidateFieldsError(ex.getFieldErrors().stream()
                .map(element -> new FieldError(element.getField(), element.getCode()))
                .toList()), HttpStatus.BAD_REQUEST);
    }
}