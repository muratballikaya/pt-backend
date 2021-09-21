package com.softtech.personalitytest.ptbackend.repository;


import com.softtech.personalitytest.ptbackend.model.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, String> {

    @Query(" { $and : [{testId : ?0} , {questionInternalId : ?1}] }")
    public Answer findAnswerOfQuestion(@Param("testId") String testId,@Param("questionInternalId") String questionInternalId);

    @Query("{testId : ?0} ")
    public List<Answer> findAllAnswers(@Param("testId") String testId);
}
