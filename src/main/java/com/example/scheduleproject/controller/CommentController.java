package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.CreateCommentRequest;
import com.example.scheduleproject.dto.CreateCommentResponse;
import com.example.scheduleproject.dto.DeleteCommentRequest;
import com.example.scheduleproject.dto.GetCommentResponse;
import com.example.scheduleproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("schedules/{sId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long sId,
            @RequestBody @Valid CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(sId, request));
    }

    @DeleteMapping("schedules/{sId}/comments/{commentId}")
    private ResponseEntity<Void> delete(@PathVariable Long commentId, @RequestBody @Valid DeleteCommentRequest request) {
        commentService.delete(commentId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
