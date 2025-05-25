package com.example.BE_Assignment2.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {
    private String task;
    private String password;
    private String user_name;
}
