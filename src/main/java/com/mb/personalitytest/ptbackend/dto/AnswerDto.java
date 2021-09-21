package com.mb.personalitytest.ptbackend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Answer model documentation", description = "Model")
public class AnswerDto {
    @ApiModelProperty(value = "Unique id field of question data")
    private BigInteger id;
    @ApiModelProperty(value = "What test this answer belongs to")
    @NotNull(message = "testId is requirde")
    private String testId;
    @ApiModelProperty(value = "Question sequence ID")
    @Positive(message = "sequence should be positive integer")
    private int sequence;
    @ApiModelProperty(value = "Question conditional sequence ID")
    @PositiveOrZero(message = "conditional sequence should be zero or positive")
    private int conditionalSequence;
    @ApiModelProperty(value = "Answered text")
    @NotNull(message = "answer is required")
    private String answer;
    private String questionInternalId;
    private String category;
}
