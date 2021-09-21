package com.softtech.personalitytest.ptbackend.repository;

import com.softtech.personalitytest.ptbackend.model.Answer;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnswerRepositoryIT extends  BaseRepositoryIT{

    @Autowired
    AnswerRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldFindAllAnswers() {
        this.repository.save(Answer.builder().answer("a1").testId("t1").questionInternalId("c-1-1").build());
        this.repository.save(Answer.builder().answer("a2").testId("t2").questionInternalId("c-1-2").build());

        List<Answer> answers = repository.findAllAnswers("t1");

        Assert.assertEquals(2,answers.size());
    }
}
