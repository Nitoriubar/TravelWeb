package com.webservice.web.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.webservice.web.api.entity.user.User;
import com.webservice.web.api.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
	
    private UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}
