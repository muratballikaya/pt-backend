package com.mb.personalitytest.ptbackend.dto.setup;

import lombok.*;

import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Range {
    @Positive(message = "'from' should be positive")
    public int from;
    @Positive(message = "'to' should be positive")
    public int to;
}
