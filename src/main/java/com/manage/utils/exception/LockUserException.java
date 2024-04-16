package com.manage.utils.exception;

public class LockUserException extends RuntimeException {
    public  LockUserException (String message){
        super(message);
    }
}