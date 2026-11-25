package org.learningfromexperience.demo.orders.api;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> badRequest(IllegalArgumentException ex){return ResponseEntity.badRequest().body(Map.of("error",ex.getMessage()));}
    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<Map<String,String>> rollback(UnexpectedRollbackException ex){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Order confirmation could not be completed"));}
}
