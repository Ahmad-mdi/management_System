package com.manage.utils.exception;


import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    //annotation errors (NotBlank or ...):
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> HandleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    //unique columns db:
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        if (message == null)
            return new ApiResponse<>(e.getMessage(), ApiResponseStatus.EXCEPTION);

        if (message.contains("uk_2f3xrn5enpplukjdl7e0c7rdf"))
            return new ApiResponse<>(getMessgeOfResourceBundle("already.nationalCode"), ApiResponseStatus.EXCEPTION);
        else if (message.contains("uk_r43af9ap4edm43mmtq01oddj6"))
            return new ApiResponse<>(getMessgeOfResourceBundle("already.username"), ApiResponseStatus.EXCEPTION);
        else
            return new ApiResponse<>(message, ApiResponseStatus.EXCEPTION);
    }

    //user notFound:
    @ExceptionHandler(UserNotFoundException.class)
    public ApiResponse<String> handleUserNotFoundException(UserNotFoundException e) {
        String message = e.getMessage();
        if (message == null)
            return new ApiResponse<>(e.getMessage(), ApiResponseStatus.EXCEPTION);

        return new ApiResponse<>(getMessgeOfResourceBundle("user.not.found"), ApiResponseStatus.EXCEPTION);
    }

    //locked users:
    @ExceptionHandler(LockUserException.class)
    public ApiResponse<String> handleLockUserException(LockUserException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle("user.locked"), ApiResponseStatus.EXCEPTION);
    }

    //login users:
    @ExceptionHandler(LoginException.class)
    public ApiResponse<String> handleLoginException(LoginException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle("invalid.username.password"), ApiResponseStatus.EXCEPTION);
    }

    //pass notFound:
    @ExceptionHandler(PassNotFoundException.class)
    public ApiResponse<String> handlePassNotFoundException(PassNotFoundException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle("invalid.old.password"), ApiResponseStatus.EXCEPTION);
    }

    //validate pattern for changePass:
    @ExceptionHandler(ValidateNewPasswordException.class)
    public ApiResponse<String> handleValidateNewPasswordException(ValidateNewPasswordException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle("pattren.password"), ApiResponseStatus.EXCEPTION);
    }

    //pagination:
    @ExceptionHandler(NumberFormatException.class)
    public ApiResponse<String> handleNumberFormatException(NumberFormatException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle(e.getMessage()), ApiResponseStatus.EXCEPTION);
    }

    //data notFound:
    @ExceptionHandler(DataNotFoundException.class)
    public ApiResponse<String> handleDataNotFoundException(DataNotFoundException e) {
        handleExceptionMessage(e);
        return new ApiResponse<>(getMessgeOfResourceBundle("data.not.found"), ApiResponseStatus.EXCEPTION);
    }





    //config resourceBundle (get message errors)
    public String getMessgeOfResourceBundle(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    //handle null exception (get jvm message exp)
    public void handleExceptionMessage (Throwable e){
        String message = e.getMessage();
        logger.debug(message);
    }

}


