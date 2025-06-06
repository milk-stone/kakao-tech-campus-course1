package com.example.BE_Assignment2.schedule.dto;

import com.example.BE_Assignment2.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponse {
    private String user_name;
    private String task;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
