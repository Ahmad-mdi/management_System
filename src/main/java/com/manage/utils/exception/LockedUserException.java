package com.manage.utils.exception;

public class LockedUserException extends RuntimeException{
    public  LockedUserException (String message){
        super(message);
    }
}
