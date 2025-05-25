package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleDeleteRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    public ResponseEntity<Void> createSchedule(ScheduleRequest scheduleRequest);
    public ResponseEntity<Page<ScheduleResponse>> getSchedules(String updatedAt, String name, Pageable pageable);
    public ResponseEntity<ScheduleResponse> getSchedule(Long id);
    public ResponseEntity<Void> updateSchedule(Long id, ScheduleUpdateRequest scheduleUpdateRequest);
    public ResponseEntity<Void> deleteSchedule(Long id, ScheduleDeleteRequest request);
}
