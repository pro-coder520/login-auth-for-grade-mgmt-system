package com.example.studentgrademanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class StudentGradeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentGradeManagementApplication.class, args);
    }

}