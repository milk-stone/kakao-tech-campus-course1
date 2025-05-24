package com.example.BE_Assignment2.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponse {
    private String user_name;
    private String task;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
