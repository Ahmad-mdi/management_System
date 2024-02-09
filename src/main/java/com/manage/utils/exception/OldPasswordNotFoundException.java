package com.manage.utils.exception;

public class OldPasswordNotFoundException extends RuntimeException{
    public  OldPasswordNotFoundException (String message){
        super(message);
    }
}
