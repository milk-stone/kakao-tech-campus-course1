package com.example.BE_Assignment2.schedule.controller;

import com.example.BE_Assignment2.schedule.dto.ScheduleDeleteRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 1. 일정 생성
    @PostMapping("/create")
    public ResponseEntity<Void> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return scheduleService.createSchedule(scheduleRequest);
    }

    // 2. 전체 일정 조회
    // GET /schedules?updatedAt=2025-05-25&author=홍길동&page=0&size=5
    @GetMapping
    public ResponseEntity<Page<ScheduleResponse>> getSchedules(@RequestParam(required = false) String updatedAt,
                                                               @RequestParam(required = false) String name,
                                                               Pageable pageable) {
        return scheduleService.getSchedules(updatedAt, name, pageable);
    }


//    // 3. 특정 일정 조회
//    @GetMapping("/{schedule_id}")
//    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable("schedule_id") Long schedule_id) {
//
//        return ResponseEntity.ok(new ScheduleResponse());
//    }
//
//    // 4. 특정 일정 수정
//    @PutMapping("/{schedule_id}")
//    public ResponseEntity<Void> updateSchedule(@PathVariable("schedule_id") Long schedule_id, ScheduleRequest scheduleRequest) {
//
//
//        return ResponseEntity.ok().build();
//    }
//
//    // 5. 선택한 일정 삭제
//    // DELETE /schedules/5 + Json body
//    @DeleteMapping("/{schedule_id}")
//    public ResponseEntity<Void> deleteSchedule(@PathVariable("schedule_id") Long schedule_id, @RequestBody ScheduleDeleteRequest req) {
//
//        return ResponseEntity.ok().build();
//    }
}
