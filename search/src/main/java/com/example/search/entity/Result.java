package com.example.search.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Result")
public class Result implements Serializable {

    @ApiModelProperty("Student List")
    List<Student> students;
    @ApiModelProperty("University List")
    List<University> universities;

}
