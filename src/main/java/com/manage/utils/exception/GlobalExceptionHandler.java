package com.manage.utils.exception;

import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler{
    private final MessageSource messageSource;
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
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        if (message == null) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (message.contains("uk_2f3xrn5enpplukjdl7e0c7rdf")) {
            return new ResponseEntity<>(getMessage("already.nationalCode"), HttpStatus.BAD_REQUEST);
        } else if (message.contains("uk_r43af9ap4edm43mmtq01oddj6")) {
            return new ResponseEntity<>(getMessage("already.username"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleUserNotFoundException(LoginException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(OldPasswordNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(OldPasswordNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(DataNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(LockedUserException.class)
    public ResponseEntity<String> handleIllegalArgumentException(LockedUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessage(e.getMessage()));
    }
    @ExceptionHandler(ValidateNewPasswordException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ValidateNewPasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getMessage(e.getMessage()));
    }


    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

}


