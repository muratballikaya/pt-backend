package com.mb.personalitytest.ptbackend.exception;

public class SetupException extends RuntimeException{

    public SetupException(String message, Exception e){
        super(message,e);
    }
}
