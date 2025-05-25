package com.example.BE_Assignment2.user.service;

import com.example.BE_Assignment2.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Void> createUser(UserRequest request);
}
