package com.example.university.service;

import com.example.university.entity.University;

import java.util.List;

public interface UniversityService {

    List<University> searchByName(String name);

    List<University> searchByCountry(String country);

}
