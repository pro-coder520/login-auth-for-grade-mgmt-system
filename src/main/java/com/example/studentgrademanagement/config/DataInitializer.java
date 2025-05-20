package com.example.studentgrademanagement.config;

import com.example.studentgrademanagement.model.Role;
import com.example.studentgrademanagement.model.User;
import com.example.studentgrademanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createDefaultAdminUser();
        // You can add more initial data here if needed
        createDefaultStudentUser();
        createDefaultLecturerUser();
    }

    private void createDefaultAdminUser() {
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@example.com")
                    .firstName("Admin")
                    .lastName("User")
                    .role(Role.ROLE_ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(adminUser);
            System.out.println("Default admin user created.");
        }
    }
    private void createDefaultStudentUser() {
        if (!userRepository.existsByUsername("student")) {
            User studentUser = User.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student123"))
                    .email("student@example.com")
                    .firstName("Student")
                    .lastName("One")
                    .role(Role.ROLE_STUDENT)
                    .enabled(true)
                    .build();
            userRepository.save(studentUser);
            System.out.println("Default student user created.");
        }
    }
    private void createDefaultLecturerUser() {
        if (!userRepository.existsByUsername("lecturer")) {
            User lecturerUser = User.builder()
                    .username("lecturer")
                    .password(passwordEncoder.encode("lecturer123"))
                    .email("lecturer@example.com")
                    .firstName("Lecturer")
                    .lastName("Pro")
                    .role(Role.ROLE_LECTURER)
                    .enabled(true)
                    .build();
            userRepository.save(lecturerUser);
            System.out.println("Default lecturer user created.");
        }
    }
}