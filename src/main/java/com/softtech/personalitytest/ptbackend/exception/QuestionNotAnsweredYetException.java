package com.softtech.personalitytest.ptbackend.exception;

public class QuestionNotAnsweredYetException extends  RuntimeException{

    public QuestionNotAnsweredYetException(){
        super("This question has not been answered yet..");
    }
}
