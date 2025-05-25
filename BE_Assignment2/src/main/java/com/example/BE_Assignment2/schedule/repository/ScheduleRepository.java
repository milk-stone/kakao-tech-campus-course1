package com.example.BE_Assignment2.schedule.repository;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduleRepository {
    Long save(ScheduleRequest request);
    Page<ScheduleResponse> findAllByConditions(String updatedAt, String name, Pageable pageable);
    Optional<ScheduleResponse> findById(Long id);
    Optional<Long> updateById(Long id, ScheduleUpdateRequest request);
    boolean deleteById(Long id, String password);
}
