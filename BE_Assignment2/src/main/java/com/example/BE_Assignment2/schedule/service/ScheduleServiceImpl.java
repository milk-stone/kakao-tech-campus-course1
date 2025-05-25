package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import com.example.BE_Assignment2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public ResponseEntity<ScheduleResponse> getSchedule(Long id){
        Optional<ScheduleResponse> res = scheduleRepository.findById(id);
        if (res.isPresent()){
            return ResponseEntity.ok(res.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> updateSchedule(Long id, ScheduleUpdateRequest request){
        Optional<Long> updated = scheduleRepository.updateById(id, request);
        if (updated.isPresent()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
