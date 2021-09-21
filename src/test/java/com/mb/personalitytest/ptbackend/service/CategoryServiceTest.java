package com.mb.personalitytest.ptbackend.service;

import com.mb.personalitytest.ptbackend.exception.NoCategoryFoundException;
import com.mb.personalitytest.ptbackend.model.Category;
import com.mb.personalitytest.ptbackend.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryService categoryService;

    private List<Category> generateMockCategoryData() {
        List<Category> categoryList = new ArrayList<Category>();
        categoryList.add(Category.builder().testId("testid").name("c1").id(BigInteger.ONE).build());
        categoryList.add(Category.builder().testId("testid").name("c2").id(BigInteger.TWO).build());
        return  categoryList;
    }

    @Test
    public void shouldFetchCategoryList(){
        Mockito.when(repository.fetchCategoryListByTestId(Mockito.any())).thenReturn(generateMockCategoryData());
        Assertions.assertEquals(categoryService.fetchCategoryList("testid").size() , 2);
    }

    @Test
    public void shouldThrowException(){
        Mockito.when(repository.fetchCategoryListByTestId(Mockito.any())).thenReturn(new ArrayList<Category>());
        Assertions.assertThrows(NoCategoryFoundException.class, () -> categoryService.fetchCategoryList("testid"));
    }
}
