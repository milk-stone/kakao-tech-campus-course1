package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleDeleteRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    ResponseEntity<Void> createSchedule(ScheduleRequest scheduleRequest);
    ResponseEntity<Page<ScheduleResponse>> getSchedules(String updatedAt, String email, Pageable pageable);
    ResponseEntity<ScheduleResponse> getSchedule(Long id);
    ResponseEntity<Void> updateSchedule(Long id, ScheduleUpdateRequest scheduleUpdateRequest);
    ResponseEntity<Void> deleteSchedule(Long id, ScheduleDeleteRequest request);
}
