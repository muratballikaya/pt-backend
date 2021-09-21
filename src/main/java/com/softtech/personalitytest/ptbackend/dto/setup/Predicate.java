package com.softtech.personalitytest.ptbackend.dto.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Predicate {
    @NotEmpty(message = "exactEquals should not be null")
    public List<String> exactEquals;
}
