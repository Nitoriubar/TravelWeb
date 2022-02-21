package com.webservice.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.web.exception.ResourceNotFoundException;
import com.webservice.web.model.User;
import com.webservice.web.repository.UserRepository;
import com.webservice.web.security.CurrentUser;
import com.webservice.web.security.UserPrincipal;

@RestController //사용자가 요청 -> 응답(data) @Controller는 사용자가 요청하면 응답을 html파일로 함.
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
//    @GetMapping("/http/get") // http/get
//    public String getTest() {
//    	return "get요청";
//    }

    @GetMapping("/user/me") //
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}