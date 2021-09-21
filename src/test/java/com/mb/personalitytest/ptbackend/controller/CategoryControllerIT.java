package com.mb.personalitytest.ptbackend.controller;

import com.mb.personalitytest.ptbackend.PtBackendApplication;
import com.mb.personalitytest.ptbackend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class)
@ContextConfiguration(classes={PtBackendApplication.class})
public class CategoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;


    @Test
    public void  shouldListCategoriesBeSuccessfull() throws Exception {
        mockMvc.perform(get("/api/category/testid")
                .contentType("application/json").content("{\"name\" : \"c1\"}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void  shouldListCategoriesFailWithClientError() throws Exception {
        mockMvc.perform(post("/api/category/testid")
                .contentType("application/json").content("{\"id\" : \"id\"}"))
                .andExpect(status().is4xxClientError());
    }

}
