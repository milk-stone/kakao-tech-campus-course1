package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public ResponseEntity<Void> createSchedule(ScheduleRequest scheduleRequest){
        Long result = scheduleRepository.save(scheduleRequest);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<ScheduleResponse>> getSchedules(String updatedAt, String name, Pageable pageable){
        Page<ScheduleResponse> result = scheduleRepository.findAllByConditions(updatedAt, name, pageable);
        return ResponseEntity.ok(result);
    }
}
