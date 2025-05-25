package com.example.BE_Assignment2.schedule.repository;

import com.example.BE_Assignment2.exception.EntityNotFoundException;
import com.example.BE_Assignment2.exception.PasswordMismatchException;
import com.example.BE_Assignment2.schedule.dto.ScheduleRequest;
import com.example.BE_Assignment2.schedule.dto.ScheduleResponse;
import com.example.BE_Assignment2.schedule.dto.ScheduleUpdateRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(ScheduleRequest request, Long user_id, String user_name) {
        String sql = "INSERT INTO schedule (user_id, task, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user_id.toString());
            ps.setString(2, request.getTask());
            ps.setString(3, request.getPassword()); // 평문 저장. 실제 사용 시 암호화 권장
            ps.setObject(4, now);
            ps.setObject(5, now);
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null){
            return null;
        }
        return keyHolder.getKey().longValue(); // 생성된 ID 반환
    }

    @Override
    public Page<ScheduleResponse> findAllByConditions(String updatedAt, Long user_id, Pageable pageable) {
        StringBuilder baseSql = new StringBuilder(
                "FROM schedule s JOIN user u ON s.user_id = u.user_id WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (updatedAt != null && !updatedAt.isBlank()) {
            baseSql.append(" AND DATE(s.updated_at) = ?");
            params.add(LocalDate.parse(updatedAt));
        }

        if (user_id != null && !user_id.toString().isBlank()) {
            baseSql.append(" AND s.user_id = ?");
            params.add(user_id);
        }

        // 1. total count 쿼리
        String countSql = "SELECT COUNT(*) " + baseSql;
        int total = jdbcTemplate.queryForObject(countSql, params.toArray(), Integer.class);

        // 2. 데이터 쿼리 (페이징 + 정렬)
        String dataSql = "SELECT s.*, u.user_name " + baseSql + " ORDER BY s.updated_at DESC LIMIT ? OFFSET ?";
        params.add(pageable.getPageSize());
        params.add(pageable.getOffset());

        List<ScheduleResponse> content = jdbcTemplate.query(dataSql, params.toArray(), scheduleResponseMapper());

        return new PageImpl<>(content, pageable, total);
    }
    private RowMapper<ScheduleResponse> scheduleResponseMapper() {
        return (rs, rowNum) -> new ScheduleResponse(
                rs.getString("user_name"),
                rs.getString("task"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }

    @Override
    public Optional<ScheduleResponse> findById(Long schedule_id){
        String sql = "SELECT u.user_name, s.task, s.created_at, s.updated_at FROM schedule s JOIN user u ON s.user_id = u.user_id WHERE s.schedule_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{schedule_id}, scheduleResponseMapper()));
        }
        catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("해당 일정 정보를 찾을 수 없습니다. ID: " + schedule_id);
        }
    }

    @Override
    public Optional<Long> updateById(Long schedule_id, Long user_id, ScheduleUpdateRequest request){
        String checkSql = "SELECT password FROM schedule WHERE schedule_id = ?";

        String savedPassword;
        try {
            savedPassword = jdbcTemplate.queryForObject(
                    checkSql,
                    new Object[]{schedule_id},
                    String.class
            );
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("해당 스케줄이 존재하지 않습니다.");
        }

        if (!savedPassword.equals(savedPassword)) {
            throw new PasswordMismatchException();
        }

        String schedule_sql = "UPDATE schedule SET task = ?, updated_at = NOW() WHERE schedule_id = ? AND password = ?";

        int updated_schedule = jdbcTemplate.update(
                schedule_sql,
                request.getTask(),
                schedule_id,
                request.getPassword()
        );

        String user_sql = "UPDATE user SET user_name = ? WHERE user_id = ?";
        int update_user_name = jdbcTemplate.update(user_sql, request.getUser_name(), user_id);

        if (updated_schedule > 0 || update_user_name > 0) {
            return Optional.of(schedule_id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(Long schedule_id, String password){ // id : schedule_id
        String checkSql = "SELECT password FROM schedule WHERE schedule_id = ?";

        String savedPassword;
        try {
            savedPassword = jdbcTemplate.queryForObject(
                    checkSql,
                    new Object[]{schedule_id},
                    String.class
            );
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("해당 스케줄이 존재하지 않습니다. ID = " + schedule_id);
        }

        // 비밀번호 검증
        if (!savedPassword.equals(password)) {
            throw new PasswordMismatchException();
        }

        String sql = "DELETE FROM schedule WHERE schedule_id = ? AND password = ?";
        int afftedRows = jdbcTemplate.update(sql, schedule_id, password);
        return afftedRows > 0;
    }
}
