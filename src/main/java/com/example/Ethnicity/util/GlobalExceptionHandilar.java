package com.example.Ethnicity.util;

import com.example.Ethnicity.enums.RestApiResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;
@RestControllerAdvice

public class GlobalExceptionHandilar {



        @ExceptionHandler(Exception.class)
        public static ResponseEntity<?> handleExceptions(Exception e) {

            if (e instanceof MethodArgumentNotValidException) {
                StringBuilder errorMessage = new StringBuilder();
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
                ex.getBindingResult().getFieldErrors().forEach(error -> {
                    errorMessage.append(error.getDefaultMessage()).append(" ");
                });
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseWrapper<>(RestApiResponseStatus.BAD_REQUEST.getCode(),
                                RestApiResponseStatus.BAD_REQUEST.getStatus(), errorMessage.toString()));
            }

            if (e instanceof NoSuchElementException) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<>(RestApiResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                                RestApiResponseStatus.INTERNAL_SERVER_ERROR.getStatus(), ValidationMessages.INVALID_ID));
            }

            if (e instanceof NoResourceFoundException) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<>(RestApiResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                                RestApiResponseStatus.INTERNAL_SERVER_ERROR.getStatus(),
                                ValidationMessages.WRONG_API_PATH));
            }

            if (e instanceof MethodArgumentTypeMismatchException) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<>(RestApiResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                                RestApiResponseStatus.INTERNAL_SERVER_ERROR.getStatus(),
                                ValidationMessages.MISMATCH_INPUT));
            }

            if (e instanceof HttpMessageNotReadableException) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseWrapper<>(RestApiResponseStatus.INTERNAL_SERVER_ERROR.getCode(),
                                RestApiResponseStatus.INTERNAL_SERVER_ERROR.getStatus(), ValidationMessages.WRONG_JSON));
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseWrapper<>(
                    RestApiResponseStatus.INTERNAL_SERVER_ERROR.getCode(), e.getMessage(), e.toString()));
        }
    }

