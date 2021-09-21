package com.mb.personalitytest.ptbackend.exception;

public class NoMoreQuestionException extends  RuntimeException{
    public NoMoreQuestionException(){
        super("There is no more question");
    }
}
