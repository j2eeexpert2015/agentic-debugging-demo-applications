package org.learningfromexperience.demo.boot.api;

import java.util.Map;
import org.learningfromexperience.demo.boot.service.InvalidSkuException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FulfillmentErrorHandler {
    @ExceptionHandler(InvalidSkuException.class)
    public ResponseEntity<Map<String, String>> invalidSku(InvalidSkuException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", error.getMessage()));
    }
}
