package com.example.BE_Assignment2.schedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private String email;

//    private String name;
    @NotBlank(message = "할일은 필수 항목입니다.")
    @Size(max = 200, message = "할일은 200자 이내로 입력해야 합니다.")
    private String task;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;
}
