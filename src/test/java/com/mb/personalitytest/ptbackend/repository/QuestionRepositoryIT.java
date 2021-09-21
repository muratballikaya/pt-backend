package com.mb.personalitytest.ptbackend.repository;

import com.mb.personalitytest.ptbackend.model.Question;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class QuestionRepositoryIT extends BaseRepositoryIT {

    @Autowired
    QuestionRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldFindAllCategoriesByTestId() {
        this.repository.save(Question.builder().testId("t1").question("q1").questionType("t1").category("c1").options(Arrays.asList("o1")).build());
        this.repository.save(Question.builder().testId("t1").question("q2").questionType("t1").category("c1").options(Arrays.asList("o1")).build());

        List<Question> questions = repository.findQuestions("t1","c1");

        Assert.assertEquals(2, questions.size());
    }
}
