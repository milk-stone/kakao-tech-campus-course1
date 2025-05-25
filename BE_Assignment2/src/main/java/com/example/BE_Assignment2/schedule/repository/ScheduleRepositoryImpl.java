package com.example.BE_Assignment2.schedule.repository;

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
    public Long save(ScheduleRequest request) {
        String sql = "INSERT INTO schedule (email, user_name, task, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getEmail());
            ps.setString(2, request.getName());
            ps.setString(3, request.getTask());
            ps.setString(4, request.getPassword()); // 평문 저장. 실제 사용 시 암호화 권장
            ps.setObject(5, now);
            ps.setObject(6, now);
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null){
            return null;
        }
        return keyHolder.getKey().longValue(); // 생성된 ID 반환
    }

    @Override
    public Page<ScheduleResponse> findAllByConditions(String updatedAt, String name, Pageable pageable) {
        StringBuilder baseSql = new StringBuilder("FROM schedule WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (updatedAt != null && !updatedAt.isBlank()) {
            baseSql.append(" AND DATE(updated_at) = ?");
            params.add(LocalDate.parse(updatedAt));
        }

        if (name != null && !name.isBlank()) {
            baseSql.append(" AND user_name = ?");
            params.add(name);
        }

        // 1. total count 쿼리
        String countSql = "SELECT COUNT(*) " + baseSql;
        int total = jdbcTemplate.queryForObject(countSql, params.toArray(), Integer.class);

        // 2. 데이터 쿼리 (페이징 + 정렬)
        String dataSql = "SELECT * " + baseSql + " ORDER BY updated_at DESC LIMIT ? OFFSET ?";
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
    public Optional<ScheduleResponse> findById(Long id){
        String sql = "SELECT user_name, task, created_at, updated_at FROM schedule WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{id}, scheduleResponseMapper()));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> updateById(Long id, ScheduleUpdateRequest request){
        String sql = "UPDATE schedule SET task = ?, user_name = ?, updated_at = NOW() WHERE id = ? AND password = ?";

        int updated = jdbcTemplate.update(
                sql,
                request.getTask(),
                request.getUser_name(),
                id,
                request.getPassword()
        );
        System.out.println(updated);

        if (updated > 0) {
            return Optional.of(id);
        } else {
            return Optional.empty();
        }
    }
}
