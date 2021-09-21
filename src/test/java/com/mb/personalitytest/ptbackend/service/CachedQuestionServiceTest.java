package com.mb.personalitytest.ptbackend.service;

import com.mb.personalitytest.ptbackend.exception.NoQuestionFoundForTestException;
import com.mb.personalitytest.ptbackend.model.Question;
import com.mb.personalitytest.ptbackend.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class  CachedQuestionServiceTest{

        @Mock
        QuestionRepository repository;

        @InjectMocks
        CachedQuestionService service;

        @Test
        public void shouldFetchQuestionMap(){
                Mockito.when(repository.findQuestions(Mockito.any(),Mockito.any())).thenReturn(generateMockQuestionData());

                Assertions.assertEquals(service.fetchQuestionMap("","").size(),3);
                Assertions.assertTrue(service.fetchQuestionMap("","").containsKey("1-0"));
                Assertions.assertTrue(service.fetchQuestionMap("","").containsKey("2-1"));
        }

        @Test
        public void shouldFetchQuestionMapThrowException(){
                Mockito.when(repository.findQuestions(Mockito.any(),Mockito.any())).thenReturn(new ArrayList<Question>());
                Assertions.assertThrows(NoQuestionFoundForTestException.class, () -> service.fetchQuestionMap("",""));
        }

        @Test
        public void shouldThrowException(){
                Mockito.when(repository.findQuestions(Mockito.any(),Mockito.any())).thenReturn(new ArrayList<Question>());
                Assertions.assertThrows(NoQuestionFoundForTestException.class, () -> service.fetchQuestionMap("t1","c1"));
        }

        private List<Question> generateMockQuestionData() {
                List<Question> questionList = new ArrayList<Question>();
                questionList.add(Question.builder().testId("t1").category("c1").question("q1").questionType("t1").sequence(1).conditionalSequence(0).build());
                questionList.add(Question.builder().testId("t1").category("c1").question("q2").questionType("single_choice_conditional").sequence(2).conditionalSequence(1).predicateValue("p1").build());
                questionList.add(Question.builder().testId("t1").category("c1").question("q3").questionType("t3").sequence(2).conditionalSequence(2).build());
                return questionList;
        }
}
