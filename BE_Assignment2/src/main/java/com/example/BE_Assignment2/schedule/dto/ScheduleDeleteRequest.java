package com.example.BE_Assignment2.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequest {
    @NotBlank(message = "이메일은 필수 항목입니다.")
    private String password;
}
