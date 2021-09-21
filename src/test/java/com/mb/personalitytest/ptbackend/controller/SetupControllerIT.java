package com.mb.personalitytest.ptbackend.controller;

import com.mb.personalitytest.ptbackend.PtBackendApplication;
import com.mb.personalitytest.ptbackend.service.SetupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SetupController.class)
@ContextConfiguration(classes={PtBackendApplication.class})
public class SetupControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SetupService answerService;

    @Test
    public void  shouldSetupBeSuccessfull() throws Exception {
        mockMvc.perform(post("/api/setup")
                .contentType("application/json").content(validJson()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void  shouldSetupFailWithClientError() throws Exception {
        mockMvc.perform(post("/api/setup")
                .contentType("application/json").content("{}"))
                .andExpect(status().is4xxClientError());
    }


    private String validJson(){
        return "{\n" +
                "  \"categories\": [\n" +
                "    \"hard_fact\",\n" +
                "    \"lifestyle\",\n" +
                "    \"introversion\",\n" +
                "    \"passion\"\n" +
                "  ],\n" +
                "  \"questions\": [\n" +
                "\n" +
                "       {\n" +
                " \"question\": \"How important is the age of your partner to you?\",\n" +
                " \"category\": \"hard_fact\",\n" +
                " \"question_type\": {\n" +
                " \"type\": \"single_choice_conditional\",\n" +
                " \"options\": [\n" +
                " \"not important\",\n" +
                " \"important\",\n" +
                " \"very important\"\n" +
                " ],\n" +
                " \"condition\": {\n" +
                " \"predicate\": {\n" +
                " \"exactEquals\": [\n" +
                " \"${selection}\",\n" +
                " \"very important\"\n" +
                " ]\n" +
                " },\n" +
                " \"if_positive\": {\n" +
                " \"question\": \"What age should your potential partner be?\",\n" +
                " \"category\": \"hard_fact\",\n" +
                " \"question_type\": {\n" +
                " \"type\": \"number_range\",\n" +
                " \"range\": {\n" +
                " \"from\": 18,\n" +
                " \"to\": 140\n" +
                " }\n" +
                " }\n" +
                " }\n" +
                " }\n" +
                " }\n" +
                " }\n" +
                "\n" +
                "\n" +
                "  ]\n" +
                "}";
    }

}
