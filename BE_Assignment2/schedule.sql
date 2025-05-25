-- 작성자 정보
-- 학교 : 경북대학교
-- 트랙 : Backend
-- 이름 : 유우석

-- Lv 2 이하

-- CREATE TABLE schedule (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     email VARCHAR(100) NOT NULL,
--     user_name VARCHAR(100) NOT NULL,
--     task VARCHAR(255) NOT NULL,
--     password VARCHAR(100) NOT NULL,
--     created_at DATETIME NOT NULL,
--     updated_at DATETIME NOT NULL
-- );

-- Lv 3 이상

-- User 테이블 생성
CREATE TABLE user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Schedule 테이블 생성
CREATE TABLE schedule (
    schedule_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(user_id)
);