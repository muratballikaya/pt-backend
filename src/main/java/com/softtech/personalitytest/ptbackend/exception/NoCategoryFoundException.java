package com.softtech.personalitytest.ptbackend.exception;

public class NoCategoryFoundException extends RuntimeException{
    public NoCategoryFoundException(String testId){
        super("No category found related with " + testId);
    }
}
