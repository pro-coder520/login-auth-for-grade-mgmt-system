package com.example.studentgrademanagement.controller;

import com.example.studentgrademanagement.dto.response.UserInfoResponse;
import com.example.studentgrademanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT') or hasRole('LECTURER') or hasRole('ADMIN')")
    public String studentAccess() {
        return "Student Content.";
    }

    @GetMapping("/lecturer")
    @PreAuthorize("hasRole('LECTURER') or hasRole('ADMIN')")
    public String lecturerAccess() {
        return "Lecturer Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/user-info")
    @PreAuthorize("isAuthenticated()") // Any authenticated user can access this
    public ResponseEntity<UserInfoResponse> getUserInformation() {
        return ResponseEntity.ok(userService.getCurrentUserInfo());
    }
}