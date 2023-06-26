package com.example.student.entity;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "teacher_student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherStudent{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id")
    private Teacher teacher;


    public TeacherStudent(Student student, Teacher teacher) {
        this(null, student, teacher);
    }

    @Override
    public String toString() {
        return "TeacherStudent{" +
                "id=" + id +
                ", s_id=" + student.getId() +
                ", t_id=" + teacher.getId() +
                '}';
    }

}