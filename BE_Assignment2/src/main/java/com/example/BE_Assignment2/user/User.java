package com.example.BE_Assignment2.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long user_id;
    private String user_name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
