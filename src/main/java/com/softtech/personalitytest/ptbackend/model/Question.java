package com.softtech.personalitytest.ptbackend.model;

import com.softtech.personalitytest.ptbackend.helper.QuestionHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    private BigInteger id;
    private String testId;
    private String category;
    private String question;
    private List<String> options;
    private int sequence;
    private String questionType;
    private String predicateValue;
    private int conditionalSequence;

    public Question getItSelf(){
        return this;
    }

    public String internalId(){
        return QuestionHelper.generateInternalId(sequence,conditionalSequence);
    }
}
