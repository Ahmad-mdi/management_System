package com.manage.utils.exception;

import com.manage.constance.UserConstants;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> HandleInvalidArguments(MethodArgumentNotValidException ex)
    {
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (Objects.requireNonNull(ex.getMessage()).contains("uk_2f3xrn5enpplukjdl7e0c7rdf"))
            return new ApiResponse<>(UserConstants.INVALID_NATIONAL_CODE, ApiResponseStatus.EXCEPTION);
         else
            return new ApiResponse<>(UserConstants.DATA_INTEGRITY, ApiResponseStatus.EXCEPTION);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id format");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /*@ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleNullPointerException(NullPointerException ex) {
        return new ApiResponse<>(messageError.validUsernameAndPassword,ApiResponseStatus.EXCEPTION);
    }*/

}


