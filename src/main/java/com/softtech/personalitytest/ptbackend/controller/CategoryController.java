package com.softtech.personalitytest.ptbackend.controller;

import com.softtech.personalitytest.ptbackend.dto.setup.SetupDto;
import com.softtech.personalitytest.ptbackend.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
@Api(value = "Personal Test Category Api documentation")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{testId}")
    @ApiOperation(value = "Lists categories")
    public ResponseEntity<List<String>> listCategories(@PathVariable String testId) {
        return new ResponseEntity<List<String>>(categoryService.fetchCategoryList(testId), HttpStatus.OK);
    }

}
