package com.example.student.service;

import com.example.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(Student student);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);

    Optional<Student> getStudentById(Long id);

    List<Student> listStudents();

}