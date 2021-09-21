package com.softtech.personalitytest.ptbackend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Question Api model documentation", description = "Model")
public class QuestionDto {

    @ApiModelProperty(value = "Category  of question")
    private String category;
    @ApiModelProperty(value = "Actual question")
    private String question;
    @ApiModelProperty(value = "List of options")
    private List<String> options;
    @ApiModelProperty(value = "Sequence value of question")
    private int sequence;
    @ApiModelProperty(value = "Type of question")
    private String questionType;
    @ApiModelProperty(value = "Predicate value if this is a conditional question")
    private String predicateValue;
    @ApiModelProperty(value = "Conditional sequence value of question")
    private int conditionalSequence;

}
