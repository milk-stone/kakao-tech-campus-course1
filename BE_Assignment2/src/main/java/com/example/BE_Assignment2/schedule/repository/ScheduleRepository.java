package com.example.BE_Assignment2.schedule.repository;

import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ScheduleRepository {
    Long save(ScheduleRequest request, Long user_id, String user_name);
    Page<ScheduleResponse> findAllByConditions(String updatedAt, Long user_id, Pageable pageable);
    Optional<ScheduleResponse> findById(Long id);
    Optional<Long> updateById(Long id, ScheduleUpdateRequest request);
    boolean deleteById(Long id, String password);
}
