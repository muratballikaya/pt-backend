package com.softtech.personalitytest.ptbackend.exception;

public class NoQuestionFoundForTestException extends  RuntimeException{

    public NoQuestionFoundForTestException(String testId, String category){
        super("No question found for test related with category : " + category + " - testId " + testId);
    }
}
