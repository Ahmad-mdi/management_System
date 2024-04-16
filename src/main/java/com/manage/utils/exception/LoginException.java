package com.manage.utils.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class LoginException extends RuntimeException {

    public  LoginException (String message){
        super(message);
    }
}

