package by.tms.restproject.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> re(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> messages = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messages.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }

}
