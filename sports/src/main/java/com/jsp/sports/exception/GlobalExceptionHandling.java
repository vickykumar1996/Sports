package com.jsp.sports.exception;

import com.jsp.sports.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handelException(ResourceNotFoundException r , WebRequest webRequest){
        ErrorDto errorDto = new ErrorDto(new Date(),r.getMessage(),webRequest.getDescription(true));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>handel(Exception e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handelValidation(MethodArgumentNotValidException ex){
        List<String> error = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getDefaultMessage()).toList();
        return ResponseEntity.ok(error);
    }
}
