package com.mb.personalitytest.ptbackend.controller;

import com.mb.personalitytest.ptbackend.PtBackendApplication;
import com.mb.personalitytest.ptbackend.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionController.class)
@ContextConfiguration(classes={PtBackendApplication.class})
public class QuestionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    QuestionService questionController;

    @Test
    public void  shouldNextQuestionBeSuccessfull() throws Exception {

        mockMvc.perform(get("/api/question/test/cat/1/0")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
