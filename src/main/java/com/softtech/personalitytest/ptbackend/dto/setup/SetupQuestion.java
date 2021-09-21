package com.softtech.personalitytest.ptbackend.dto.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetupQuestion {

    @NotNull(message = "question is required")
    public String question;
    @NotNull(message = "category is required")
    public String category;
    @NotNull(message = "question type is required")
    public QuestionType question_type;
}
