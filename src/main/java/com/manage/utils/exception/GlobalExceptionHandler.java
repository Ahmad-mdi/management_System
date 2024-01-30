package com.manage.utils.exception;

import com.manage.config.ConstantsMessage;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler{
    private final ConstantsMessage messageError;
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
            return new ApiResponse<>(messageError.nationalCodeUnique, ApiResponseStatus.EXCEPTION);
         else
            return new ApiResponse<>(messageError.dataIntegrity, ApiResponseStatus.EXCEPTION);
    }

}


