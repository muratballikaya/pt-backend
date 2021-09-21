package com.mb.personalitytest.ptbackend.repository;

import com.mb.personalitytest.ptbackend.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

    @Query("{testId : ?0}")
    public List<Category> fetchCategoryListByTestId(@Param("testId") String testId);
}
