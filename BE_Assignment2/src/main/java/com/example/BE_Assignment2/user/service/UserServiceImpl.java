package com.example.BE_Assignment2.user.service;

import com.example.BE_Assignment2.user.dto.UserRequest;
import com.example.BE_Assignment2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Void> createUser(UserRequest request){
        Long result = userRepository.save(request);
        if (result == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
