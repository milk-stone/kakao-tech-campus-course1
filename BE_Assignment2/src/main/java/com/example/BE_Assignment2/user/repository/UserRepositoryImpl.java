package com.example.BE_Assignment2.user.repository;

import com.example.BE_Assignment2.user.dto.UserRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(UserRequest request){
        try {
            String sql = "INSERT INTO user (user_name, email, created_at, updated_at) VALUES (?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, request.getUsername());
                ps.setString(2, request.getEmail());
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                return ps;
            }, keyHolder);

            Number key = keyHolder.getKey();
            if (key == null) {
                return null;
            }
            return keyHolder.getKey().longValue();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다: " + request.getEmail());
        } catch (DataAccessException e){
            throw new RuntimeException("DB 접근 중 오류가 발생했습니다.", e);
        }
    }
}
