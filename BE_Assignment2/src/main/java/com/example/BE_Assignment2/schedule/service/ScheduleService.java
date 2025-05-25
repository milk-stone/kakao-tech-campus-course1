package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    public ResponseEntity<Void> createSchedule(ScheduleRequest scheduleRequest);
    public ResponseEntity<Page<ScheduleResponse>> getSchedules(String updatedAt, String name, Pageable pageable);
    public ResponseEntity<ScheduleResponse> getSchedule(Long id);
}
