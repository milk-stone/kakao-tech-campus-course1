package com.example.BE_Assignment2.user.repository;

import com.example.BE_Assignment2.user.User;
import com.example.BE_Assignment2.user.dto.UserRequest;

import java.util.Optional;

public interface UserRepository {
    Long save(UserRequest request);
    Optional<User> findUserByEmail(String email);
}
