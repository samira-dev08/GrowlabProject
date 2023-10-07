package com.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleCustomerNotFound(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<Object> handleArgumentException(MethodArgumentNotValidException ex, WebRequest request)  {
//        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
//                ex.getBindingResult().getFieldError().getDefaultMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)  {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
