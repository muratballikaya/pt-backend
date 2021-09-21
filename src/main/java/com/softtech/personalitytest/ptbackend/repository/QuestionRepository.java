package com.softtech.personalitytest.ptbackend.repository;

import com.softtech.personalitytest.ptbackend.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query("{ $and : [ { testId : ?0 } ,{category : ?1 } ] } ")
    List<Question> findQuestions(@Param("testId") String testId, @Param("category") String category);

    @Query("{ testId : ?0 } ")
    List<Question> findQuestionsByTestId(@Param("testId") String testId);

}
