package com.example.BE_Assignment2.user.repository;

import com.example.BE_Assignment2.user.dto.UserRequest;

public interface UserRepository {
    Long save(UserRequest request);
}
