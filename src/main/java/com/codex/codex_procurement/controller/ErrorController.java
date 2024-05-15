package com.codex.codex_procurement.controller;

import com.codex.codex_procurement.dto.response.CommonResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

public class ErrorController {

//    error hhttprequest
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<CommonResponse<?>> responseResponseEntityStatusException (ResponseStatusException exception) {
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(exception.getStatusCode().value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(response);
    }

//    error validation
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<CommonResponse<?>> responseResponseEntityConstraintViolationException (ConstraintViolationException exception) {
        CommonResponse<?> response = CommonResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
