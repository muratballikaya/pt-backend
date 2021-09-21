package com.softtech.personalitytest.ptbackend.controller;

import com.softtech.personalitytest.ptbackend.dto.QuestionDto;
import com.softtech.personalitytest.ptbackend.dto.QuestionQueryDto;
import com.softtech.personalitytest.ptbackend.model.Answer;
import com.softtech.personalitytest.ptbackend.model.Question;
import com.softtech.personalitytest.ptbackend.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/question")
@Api(value = "Personal Test Question Api documentation")
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/{testId}/{category}/{sequence}/{cSequence}")
    @ApiOperation(value = "Fetch next question")
    public ResponseEntity<QuestionDto> nextQuestion(@PathVariable("testId") String testId, @PathVariable("category") String category,
                                                    @PathVariable("sequence") int sequence,
                                                    @PathVariable("cSequence") int csequence) {
        return new ResponseEntity<QuestionDto>(constructDto(
                questionService.fetchNextQuestion(testId, category,sequence,csequence)), HttpStatus.OK);
    }

    @GetMapping("/{testId}")
    @ApiOperation(value = "Get all questions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions(@PathVariable("testId") String testId) {
        return new ResponseEntity<List<QuestionDto>>(constructDtoList(questionService.findAllQuestions(testId) ) , HttpStatus.OK);
    }

    private List<QuestionDto> constructDtoList (List<Question> questions){
        List<QuestionDto> dtoList = new ArrayList<QuestionDto>();
        for(Question  q : questions){
            dtoList.add(constructDto(q));
        }
        return  dtoList;
    }
    private QuestionDto constructDto(Question question) {
        if(question == null) return null;
        return QuestionDto.builder().question(question.getQuestion()).
                questionType(question.getQuestionType()).
                category(question.getCategory()).
                conditionalSequence(question.getConditionalSequence()).
                options(question.getOptions()).
                sequence(question.getSequence()).build();
    }

}
