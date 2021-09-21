package com.softtech.personalitytest.ptbackend.dto.setup;

import lombok.*;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Condition {
    @NotNull(message = "predicate should not be null")
    public Predicate predicate;
    @NotNull(message = "question should not be null")
    public SetupQuestion if_positive;
}
