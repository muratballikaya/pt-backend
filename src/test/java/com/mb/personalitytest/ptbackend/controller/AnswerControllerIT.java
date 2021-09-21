package com.mb.personalitytest.ptbackend.controller;

import com.mb.personalitytest.ptbackend.PtBackendApplication;
import com.mb.personalitytest.ptbackend.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AnswerController.class)
@ContextConfiguration(classes={PtBackendApplication.class})
public class AnswerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnswerService answerService;


    @Test
    public void  shouldSaveAnswerBeSuccessfull() throws Exception {
        mockMvc.perform(put("/api/answer")
                .contentType("application/json").content("{\"testId\" : \"1\", \"sequence\" : 1,\"conditionalSequence\":0, \"answer\" : \"answer\"}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void  shouldSaveAnswerFailWithClientError() throws Exception {
        mockMvc.perform(put("/api/answer")
                .contentType("application/json").content("{\"sequence\" : 0,\"conditionalSequence\":0, \"answer\" : \"answer\"}"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldGetAllAnswersBeSuccessfull() throws Exception{
        mockMvc.perform(get("/api/answer/testid")
                .contentType("application/json"))
                .andExpect(status().is2xxSuccessful());
    }

}
