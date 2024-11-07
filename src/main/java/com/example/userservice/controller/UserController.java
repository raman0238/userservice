package com.example.userservice.controller;

import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.service.JwtService;
import com.example.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

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

    @PostMapping("/register")
    public User register(@RequestBody User student) {
        return userService.addUser(student);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }

    @PostMapping("/validateToken")
    public void validate(@RequestParam String token) {
        jwtService.validateToken(token);
    }

}
