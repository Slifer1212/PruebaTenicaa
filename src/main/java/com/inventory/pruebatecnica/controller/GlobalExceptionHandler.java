package com.inventory.pruebatecnica.controller;

import com.inventory.pruebatecnica.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Centralized exception handler for REST controllers.
 *
 * <p>Handles common domain and validation exceptions and translates them into
 * appropriate HTTP responses with a JSON body.</p>
 *
 * - MethodArgumentNotValidException -> HTTP 400 (Bad Request). The response body
 *   is a map of field names to validation error messages.
 * - ProductNotFoundException -> HTTP 404 (Not Found). The response body contains
 *   a single entry with key {@code "message"} and the exception message as value.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle validation errors raised when controller method arguments fail
     * bean validation.
     *
     * @param ex the validation exception containing binding results
     * @return a {@code ResponseEntity} with HTTP status 400 and a map where keys
     *         are field names and values are their corresponding validation messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error
                -> errors.put(error.getField() , error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handle domain-level not found errors for products.
     *
     * @param ex the exception indicating the requested product was not found
     * @return a {@code ResponseEntity} with HTTP status 404 and a map containing
     *         a {@code "message"} entry with the exception message
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDomainNotFound(
            ProductNotFoundException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
