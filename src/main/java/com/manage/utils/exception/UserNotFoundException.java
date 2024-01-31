package com.manage.utils.exception;

public class UserNotFoundException extends RuntimeException {
    public  UserNotFoundException (String message){
        super(message);
    }
}
