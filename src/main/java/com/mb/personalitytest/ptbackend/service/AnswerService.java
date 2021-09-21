package com.mb.personalitytest.ptbackend.service;


import com.mb.personalitytest.ptbackend.model.Answer;
import com.mb.personalitytest.ptbackend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private static final String SUCCESS = "success";

    @Autowired
    AnswerRepository repository;

    public Answer findAnswerOfTheQuestion(String testId, String questionInternalId) {
        return repository.findAnswerOfQuestion(testId, questionInternalId);
    }

    public String saveAnswer(Answer answer){
        Answer currentAnswer = repository.findAnswerOfQuestion(answer.getTestId(), answer.getQuestionInternalId());
        if(currentAnswer!=null) {
            currentAnswer.setAnswer(answer.getAnswer());
            repository.save(currentAnswer);
        }
        else
            repository.save(answer);
        return SUCCESS;
    }

    public List<Answer> findAllAnswers(String testId){
        return  repository.findAllAnswers(testId);
    }
}
