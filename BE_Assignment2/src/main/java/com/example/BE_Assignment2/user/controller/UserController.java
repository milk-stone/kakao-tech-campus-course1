package com.example.BE_Assignment2.user.controller;

import com.example.BE_Assignment2.user.dto.UserRequest;
import com.example.BE_Assignment2.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    // 1. 유저 등록
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    // 2. 유저 수정


    // 3.
}
