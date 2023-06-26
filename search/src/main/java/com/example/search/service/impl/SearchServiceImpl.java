package com.example.search.service.impl;

import com.example.search.entity.Result;
import com.example.search.entity.Student;
import com.example.search.entity.University;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static com.example.search.constants.Url.STUDENT_URL;
import static com.example.search.constants.Url.UNIVERSITY_URL;

@Service
public class SearchServiceImpl implements SearchService {

    private RestTemplate restTemplate;


    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Result getResult() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        Result result = new Result();
        String country = "United States";
        CompletableFuture<Void> universityFuture = CompletableFuture.runAsync(()->{
            ResponseEntity<Map> response  = restTemplate.getForEntity(UNIVERSITY_URL + "/university/search?country=" + country, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, List<University>> resultMap = response.getBody();
                List<University> universityList = resultMap.get("data");
                // get the first 10 items
                if (universityList.size() > 10) {
                    result.setUniversities(universityList.subList(0, 10));
                } else {
                    result.setUniversities(universityList);
                }
            }
        });
        CompletableFuture<Void> studentFuture = CompletableFuture.runAsync(()->{
            ResponseEntity<Map> response = restTemplate.getForEntity(STUDENT_URL + "/students", Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, List<Student>> resultMap = response.getBody();
                List<Student> studentList = resultMap.get("data");
                if (studentList.size() > 10) {
                    result.setStudents(studentList.subList(0, 10));
                } else {
                    result.setStudents(studentList);
                }
            }
        });
        CompletableFuture.allOf(universityFuture, studentFuture).join();

        return result;
    }
}
