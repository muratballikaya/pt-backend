package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.model.Answer;
import com.softtech.personalitytest.ptbackend.repository.AnswerRepository;
import com.softtech.personalitytest.ptbackend.service.AnswerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    private static final String SUCCESS = "success";

    @Mock
    AnswerRepository repository;

    @InjectMocks
    AnswerService answerService;

    @Test
    public void shouldFindAnswerOfTheQuestionDoesNotThrow(){
        Assertions.assertDoesNotThrow(()->answerService.findAnswerOfTheQuestion(Mockito.any(),Mockito.any()),"It throws some exception");
    }

    @Test
    public void shouldSaveAnswerReturnsSuccess(){
        Mockito.when(answerService.findAnswerOfTheQuestion(Mockito.any(),Mockito.any())).thenReturn(Answer.builder().build());
        Assertions.assertEquals(answerService.saveAnswer(Answer.builder().build()),SUCCESS);
    }

    @Test
    public void shouldFindAllAnswersReturnSomeData(){
        Mockito.when(repository.findAllAnswers(Mockito.any())).thenReturn(Arrays.asList(Answer.builder().build()));
        Assertions.assertEquals(answerService.findAllAnswers(Mockito.any()).size(),1);
    }


}
