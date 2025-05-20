package com.example.studentgrademanagement.service;

import com.example.studentgrademanagement.dto.response.UserInfoResponse;
import com.example.studentgrademanagement.exception.ResourceNotFoundException;
import com.example.studentgrademanagement.model.User;
import com.example.studentgrademanagement.repository.UserRepository;
import com.example.studentgrademanagement.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserInfoResponse getCurrentUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new ResourceNotFoundException("User not authenticated");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Fetch the latest user details from DB if needed, or use what's in UserDetailsImpl
        // For this example, UserDetailsImpl already has most of it.
        // User user = userRepository.findByUsername(userDetails.getUsername())
        //        .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + userDetails.getUsername()));


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getFirstName(), // Assuming UserDetailsImpl has these
                userDetails.getLastName(),  // Assuming UserDetailsImpl has these
                roles
        );
    }
}