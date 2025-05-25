package com.example.BE_Assignment2.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("요청한 엔티티를 찾을 수 없습니다.");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
