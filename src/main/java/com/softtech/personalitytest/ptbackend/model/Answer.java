package com.softtech.personalitytest.ptbackend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;


@Data
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    private BigInteger id;
    private String testId;
    private String questionInternalId;
    private String answer;
}
