package com.mb.personalitytest.ptbackend.handler;

import com.mb.personalitytest.ptbackend.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class PTExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(PTExceptionHandler.class);


    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        logger.error("Validation exception occured : ",ex.getMessage());
        return new ResponseEntity<Object>("Validation exception occured : " +ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SetupException.class})
    public ResponseEntity<Object> handleSetupException(SetupException ex){
        logger.error("Exception occured during setup : ",ex.getMessage());
        return new ResponseEntity<Object>( "Exception occured during setup : " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoCategoryFoundException.class})
    public ResponseEntity<Object> handleNoCategoryFoundException(NoCategoryFoundException ex){
        logger.error("Exception occured during fetching category list : ",ex.getMessage());
        return new ResponseEntity<Object>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoQuestionFoundForTestException.class})
    public ResponseEntity<Object> handleNoQuestionFoundException(NoQuestionFoundForTestException ex){
        logger.error("Exception occured during fetching question list : ",ex.getMessage());
        return new ResponseEntity<Object>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidQuestionParametersException.class})
    public ResponseEntity<Object> handleNoQuestionFoundWithParameters(InvalidQuestionParametersException ex){
        logger.error( ex.getMessage());
        return new ResponseEntity<Object>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {QuestionNotAnsweredYetException.class})
    public ResponseEntity<Object> handleQuestionNotAnsweredException(QuestionNotAnsweredYetException ex){
        logger.error( ex.getMessage());
        return new ResponseEntity<Object>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
