package com.softtech.personalitytest.ptbackend.repository;

import com.softtech.personalitytest.ptbackend.model.Category;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryRepositoryIT extends BaseRepositoryIT {

    @Autowired
    CategoryRepository repository;

    @AfterEach
    void cleanUp() {
        this.repository.deleteAll();
    }

    @Test
    void shouldFindAllCategoriesByTestId() {
        this.repository.save(Category.builder().testId("t1").name("c1").build());
        this.repository.save(Category.builder().testId("t1").name("c2").build());

        List<Category> categories = repository.fetchCategoryListByTestId("t1");

        Assert.assertEquals(2, categories.size());
    }
}
