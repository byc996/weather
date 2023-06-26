package com.example.search.controller;

import com.example.search.entity.BaseResponse;
import com.example.search.entity.Result;
import com.example.search.entity.Student;
import com.example.search.entity.University;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Api(value = "Search APIs")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    @ApiOperation(value = "Get data from student service and university service")
    @HystrixCommand(fallbackMethod = "getResultFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    })
    @GetMapping("/weather/search/result")
    public BaseResponse<Result> getResult() {

        Result result = searchService.getResult();
        return new BaseResponse<>(HttpStatus.OK.value(), "success", result);
    }

    public BaseResponse<Result> getResultFallBack(){
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Timeout or service error", null);
    }
}
