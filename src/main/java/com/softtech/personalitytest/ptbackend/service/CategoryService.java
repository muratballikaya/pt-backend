package com.softtech.personalitytest.ptbackend.service;

import com.softtech.personalitytest.ptbackend.exception.NoCategoryFoundException;
import com.softtech.personalitytest.ptbackend.model.Category;
import com.softtech.personalitytest.ptbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;

    public List<String> fetchCategoryList(String testid) {
        List<Category> categoryList = repository.fetchCategoryListByTestId(testid);
        if (categoryList == null || categoryList.isEmpty()) {
            throw new NoCategoryFoundException(testid);
        }
        return categoryList.stream().flatMap(c -> Stream.of(c.getName())).collect(Collectors.toList());
    }
}
