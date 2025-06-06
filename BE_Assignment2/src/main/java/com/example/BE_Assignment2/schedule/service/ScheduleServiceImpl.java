package com.example.BE_Assignment2.schedule.service;

import com.example.BE_Assignment2.schedule.dto.ScheduleDeleteRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import com.example.BE_Assignment2.schedule.repository.ScheduleRepository;
import com.example.BE_Assignment2.user.User;
import com.example.BE_Assignment2.user.service.UserService;
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
    private final UserService userService;

    @Override
    public ResponseEntity<Void> createSchedule(ScheduleRequest scheduleRequest){
        // 사용자 정보 불러오기
        Optional<User> userOpt = userService.findUserByEmail(scheduleRequest.getEmail());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("해당 이메일에 해당하는 사용자가 존재하지 않습니다: " + scheduleRequest.getEmail());
        }
        Long userId = userOpt.get().getUser_id();
        String userName = userOpt.get().getUser_name();

        Long result = scheduleRepository.save(scheduleRequest, userId, userName);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<ScheduleResponse>> getSchedules(String updatedAt, String email, Pageable pageable){
        Long userId = null;
        String userName = null;

        if (email != null && !email.isBlank()){
            // 사용자 정보 불러오기
            Optional<User> userOpt = userService.findUserByEmail(email);
            if (userOpt.isEmpty()) {
                throw new IllegalArgumentException("해당 이메일에 해당하는 사용자가 존재하지 않습니다: " + email);
            }
            userId = userOpt.get().getUser_id();
        }

        Page<ScheduleResponse> result = scheduleRepository.findAllByConditions(updatedAt, userId, pageable);
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
    public ResponseEntity<Void> updateSchedule(Long schedule_id, ScheduleUpdateRequest request){
        // 사용자 정보 불러오기
        Optional<User> userOpt = userService.findUserByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("해당 이메일에 해당하는 사용자가 존재하지 않습니다: " + request.getEmail());
        }
        Long user_id = userOpt.get().getUser_id();

        Optional<Long> updated = scheduleRepository.updateById(schedule_id, user_id, request);
        if (updated.isPresent()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteSchedule(Long id, ScheduleDeleteRequest request){
        if (scheduleRepository.deleteById(id, request.getPassword())){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
