package com.example.search.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Base Response")
public class BaseResponse<T> {

    @ApiModelProperty("Http Status Code")
    private Integer code;

    @ApiModelProperty("Message")
    private String message;

    @ApiModelProperty("Data")
    T data;

}
