package com.example.scheduleproject.global.error;

import com.example.scheduleproject.global.exception.LimitCommentException;
import com.example.scheduleproject.global.exception.NotEqualsPasswordException;
import com.example.scheduleproject.global.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // 엔티티, DTO의 필수 항목을 지키지 않았을 때
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        body.put("error", errorMessage);

        return ResponseEntity.badRequest().body(body);
    }

    // 댓글 수 초과시
    @ExceptionHandler(LimitCommentException.class)
    public ResponseEntity<Map<String, Object>> handleCommentLimit(LimitCommentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }


    // 없는 일정, 댓글 조회나 삭제시
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value()); // 404
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("path", "/schedules");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }


    // 비밀 번호 불일치시
    @ExceptionHandler(NotEqualsPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleNotEquals(NotEqualsPasswordException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 403);
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
