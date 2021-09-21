package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.dto.setup.*;
import com.softtech.personalitytest.ptbackend.model.Question;
import com.softtech.personalitytest.ptbackend.repository.CategoryRepository;
import com.softtech.personalitytest.ptbackend.repository.QuestionRepository;
import com.softtech.personalitytest.ptbackend.service.QuestionService;
import com.softtech.personalitytest.ptbackend.service.SetupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SetupServiceTest {


    private static final String CONDITIONAL = "single_choice_conditional";

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    QuestionService questionService;

    @InjectMocks
    SetupService setupService;

    @Test
    public void shouldGenerateQuestionList() {
        List<Question> questionList = setupService.persistQuestions(generateMockSetupDto(), "test_uuid");
        Assertions.assertEquals(3, questionList.size());
    }

    private SetupDto generateMockSetupDto() {
        List<String> categories = Arrays.asList("cat1", "cat2");


        QuestionType questionType1 = QuestionType.builder().type("number_range")
                .range(Range.builder().from(14).to(140).build()).
                        options(Arrays.asList("opt1", "opt2")).
                        build();

        SetupQuestion setupQuestion1 = new SetupQuestion();
        setupQuestion1.setQuestion("Question 2");
        setupQuestion1.setCategory("c1");
        setupQuestion1.setQuestion_type(questionType1);

        Predicate predicate = Predicate.builder().exactEquals(Arrays.asList("${selection}","opt1")).build();
        Condition condition = Condition.builder().predicate(predicate).if_positive(setupQuestion1).build();

        SetupQuestion setupQuestion = SetupQuestion.builder().question("Question 1").category("cat1").question_type(QuestionType.builder()
                .type(CONDITIONAL).options(Arrays.asList("opt1", "opt2")).condition(condition).build()).build();


        QuestionType questionType = QuestionType.builder().type("single_choice").options(Arrays.asList("opt1")).build();
        SetupQuestion setupQuestion2 = SetupQuestion.builder().question_type(questionType).category("cat1").question("Q3").build();
        List<SetupQuestion> questions = new ArrayList<SetupQuestion>();
        questions.add(setupQuestion);
        questions.add(setupQuestion2);


        return SetupDto.builder().questions(questions).categories(categories).build();


    }
}
