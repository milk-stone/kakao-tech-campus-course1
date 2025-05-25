package com.example.BE_Assignment2.user.service;

import com.example.BE_Assignment2.user.User;
import com.example.BE_Assignment2.user.dto.UserRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    ResponseEntity<Void> createUser(UserRequest request);
    Optional<User> findUserByEmail(String email);
}
