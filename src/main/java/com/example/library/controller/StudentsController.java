package com.example.library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
@CrossOrigin("http://localhost:3000")
@Tag(name = "Students Controller")
public class StudentsController {

    @GetMapping("/students")
    @Operation(summary = "Get students", description = "Get the names of students")
    public String getStudents() {
        return "Yuliia Skorbach, Dominik Tomaszewski";
    }
}
