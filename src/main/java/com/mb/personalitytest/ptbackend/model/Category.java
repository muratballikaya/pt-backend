package com.mb.personalitytest.ptbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;


@Data
@AllArgsConstructor
@Builder
public class Category {
    @Id
    private BigInteger id;
    private String testId;
    private String name;

}
