package com.mb.personalitytest.ptbackend.exception;

public class InvalidQuestionParametersException extends  RuntimeException{

    public InvalidQuestionParametersException(){
        super("There is no question eligible with requested parameters. Check sequence and csequence parameters.");
    }
}
