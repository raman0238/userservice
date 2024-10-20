package com.example.userservice.controller;

import com.example.userservice.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    private List<Student> students = new ArrayList<>();

    @GetMapping("/students")
    public List<Student> getStudents(HttpServletRequest request){
        students.add(new Student("raman", 1));
        students.add(new Student("raman", 1));
        return students;
    }

    @GetMapping("/token")
    public CsrfToken addStudent(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/student")
    public List<Student> addStudent(@RequestBody Student student) {
          students.add(student);
          return  students;
    }


}
