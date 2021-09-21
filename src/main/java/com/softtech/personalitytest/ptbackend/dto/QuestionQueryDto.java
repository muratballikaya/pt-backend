package com.softtech.personalitytest.ptbackend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "QuestionQueryDto model documentation", description = "Model")
public class QuestionQueryDto {

    @ApiModelProperty(value = "test id  of question")
    String testId;
    @ApiModelProperty(value = "Category  of question")
    String category;
    @ApiModelProperty(value = "Sequence number of the question")
    int sequence;
    @ApiModelProperty(value = "Conditional sequence number of question, it this is a conditional question")
    int cSequence;
}
