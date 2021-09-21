package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.model.Answer;
import com.softtech.personalitytest.ptbackend.model.Question;
import com.softtech.personalitytest.ptbackend.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    QuestionRepository repository;

    @Mock
    AnswerService answerService;

    @Mock
    CachedQuestionService cachedQuestionService;

    @InjectMocks
    QuestionService questionService;


    @Test
    public void shouldFetchFirstNonConditionalQuestion(){
        Mockito.when(cachedQuestionService.fetchQuestionMap(Mockito.any(),Mockito.any())).thenReturn(generateMockQuestionMap());
        Question question = questionService.fetchNextQuestion("t1","c1",0,0);
        Assertions.assertEquals(question.getSequence(),1);
        Assertions.assertEquals(question.getConditionalSequence(),0);
    }

    @Test
    public void shouldFetchFirstConditionalQuestion(){
        Mockito.when(cachedQuestionService.fetchQuestionMap(Mockito.any(),Mockito.any())).thenReturn(generateMockQuestionMapStartsWithConditional());
        Question question = questionService.fetchNextQuestion("t1","c1",0,0);
        Assertions.assertEquals(question.getSequence(),1);
        Assertions.assertEquals(question.getConditionalSequence(),1);
    }

    @Test
    public void shouldFetchNextConditionalQuestion(){
        Mockito.when(cachedQuestionService.fetchQuestionMap(Mockito.any(),Mockito.any())).thenReturn(generateMockQuestionMap());
        Question question = questionService.fetchNextQuestion("t1","c1",1,0);
        Assertions.assertEquals(question.getSequence(),2);
        Assertions.assertEquals(question.getConditionalSequence(),1);
    }

    @Test
    public void shouldFetchNextPositivePredicateConditionalQuestion(){
        Mockito.when(questionService.fetchQuestionMap(Mockito.any(),Mockito.any())).thenReturn(generateMockQuestionMap());
        Mockito.when(answerService.findAnswerOfTheQuestion(Mockito.any(),Mockito.any())).thenReturn(generateMockAnswerData());
        Question question = questionService.fetchNextQuestion("t1","c1",2,1);
        Assertions.assertEquals(question.getSequence(),2);
        Assertions.assertEquals(question.getConditionalSequence(),2);
    }

    @Test
    public void shouldFetchNextNegativePredicateConditionalQuestion(){
        Mockito.when(cachedQuestionService.fetchQuestionMap(Mockito.any(),Mockito.any())).thenReturn(generateMockConditionalNegativePredicateQuestionMap());
        Mockito.when(answerService.findAnswerOfTheQuestion(Mockito.any(),Mockito.any())).thenReturn(generateMockAnswerData());
        Question question = questionService.fetchNextQuestion("t1","c1",2,1);
        Assertions.assertEquals(question.getSequence(),3);
        Assertions.assertEquals(question.getConditionalSequence(),0);
    }

    private Answer generateMockAnswerData() {
        return Answer.builder().testId("t1").questionInternalId("2-1").answer("p1").build();
    }

    private Map<String,Question> generateMockConditionalNegativePredicateQuestionMap(){
        return  generateMockConditionalNegativePredicateQuestionData().stream().collect(Collectors.toMap(Question::internalId, Question::getItSelf));
    }

    private Map<String,Question> generateMockQuestionMapStartsWithConditional(){
        return  generateMockQuestionStartsWithConditionData().stream().collect(Collectors.toMap(Question::internalId, Question::getItSelf));
    }

    private Map<String,Question>  generateMockQuestionMap(){
      return  generateMockQuestionData().stream().collect(Collectors.toMap(Question::internalId, Question::getItSelf));
    }

    private List<Question> generateMockQuestionData() {
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(Question.builder().testId("t1").category("c1").question("q1").questionType("t1").sequence(1).conditionalSequence(0).build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q2").questionType("single_choice_conditional").sequence(2).conditionalSequence(1).predicateValue("p1").build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q3").questionType("t3").sequence(2).conditionalSequence(2).build());
        return questionList;
    }

    private List<Question> generateMockConditionalNegativePredicateQuestionData() {
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(Question.builder().testId("t1").category("c1").question("q1").questionType("t1").sequence(1).conditionalSequence(0).build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q2").questionType("single_choice_conditional").sequence(2).conditionalSequence(1).predicateValue("p2").build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q3").questionType("t3").sequence(2).conditionalSequence(2).build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q3").questionType("t3").sequence(3).conditionalSequence(0).build());
        return questionList;
    }
    private List<Question> generateMockQuestionStartsWithConditionData() {
        List<Question> questionList = new ArrayList<Question>();
        questionList.add(Question.builder().testId("t1").category("c1").question("q1").questionType("t1").sequence(1).conditionalSequence(1).build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q2").questionType("single_choice_conditional").sequence(1).conditionalSequence(2).build());
        questionList.add(Question.builder().testId("t1").category("c1").question("q3").questionType("t3").sequence(2).conditionalSequence(0).build());
        return questionList;
    }
}
