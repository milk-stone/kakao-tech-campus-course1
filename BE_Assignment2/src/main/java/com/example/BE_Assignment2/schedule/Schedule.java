package com.example.BE_Assignment2.schedule;

import com.example.BE_Assignment2.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long schedule_id;
    private String task;
    private String password;

    private Long user_id;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
