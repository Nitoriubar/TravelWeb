package com.webservice.web.controller;

import com.webservice.web.model.User;
import com.webservice.web.repository.UserRepository;
import com.webservice.web.security.CurrentUser;
import com.webservice.web.security.UserPrincipal;
import com.webservice.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
	}
}
