package com.softtech.personalitytest.ptbackend.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ApiModel(value = "Category model documentation", description = "Model")
public class CategoryDto {
    @ApiModelProperty(value = "Unique id field of category data")
    private long id;
    @ApiModelProperty(value = "Name of the category")
    @NotNull
    private String name;

}
