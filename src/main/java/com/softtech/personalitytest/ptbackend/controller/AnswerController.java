package com.softtech.personalitytest.ptbackend.controller;

import com.softtech.personalitytest.ptbackend.dto.AnswerDto;
import com.softtech.personalitytest.ptbackend.helper.QuestionHelper;
import com.softtech.personalitytest.ptbackend.model.Answer;
import com.softtech.personalitytest.ptbackend.service.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/answer")
@Api(value = "Personal Test Annswer Api documentation")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PutMapping
    @ApiOperation(value = "Saves answer of question")
    public ResponseEntity<String> saveAnswer(@RequestBody @Valid AnswerDto answerDto) {
        return new ResponseEntity<String>(answerService.saveAnswer(convertToEntity(answerDto)), HttpStatus.OK);
    }

    @GetMapping("/{testId}")
    @ApiOperation(value = "Get all answers")
    public ResponseEntity<List<AnswerDto>> getAllAnswers(@PathVariable("testId") String testId) {
        return new ResponseEntity<List<AnswerDto>>(convertToDtoList(answerService.findAllAnswers(testId)), HttpStatus.OK);
    }

    private List<AnswerDto> convertToDtoList(List<Answer> answers){
        List<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
        for(Answer a: answers){
            answerDtoList.add(convertToDto(a));
        }
        return answerDtoList;
    }

    private AnswerDto convertToDto(Answer answer){
        return AnswerDto.builder().id(answer.getId()).testId(answer.getTestId()).questionInternalId(answer.getQuestionInternalId()).answer(answer.getAnswer()).build();
    }

    private Answer convertToEntity(AnswerDto answerDto) {
       return Answer.builder().answer(answerDto.getAnswer())
                .testId(answerDto.getTestId())
                .questionInternalId(answerDto.getCategory()+"-"+QuestionHelper.generateInternalId(answerDto.getSequence(),answerDto.getConditionalSequence())).build();
    }
}
