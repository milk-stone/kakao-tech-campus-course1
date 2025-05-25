package com.example.BE_Assignment2.schedule.repository;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduleRepository {
    public Long save(ScheduleRequest request);
    public Page<ScheduleResponse> findAllByConditions(String updatedAt, String name, Pageable pageable);
    public Optional<ScheduleResponse> findById(Long id);
    public Optional<Long> updateById(Long id, ScheduleUpdateRequest request);
}
