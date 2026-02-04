package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.commentDto.*;
import com.example.scheduleproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/schedules/{sId}/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> update(@RequestBody @Valid UpdateCommentRequest request,
                                                        @PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(request, commentId));
    }

    @DeleteMapping("schedules/{sId}/comments/{commentId}")
    private ResponseEntity<Void> delete(@PathVariable Long commentId, @RequestBody @Valid DeleteCommentRequest request) {
        commentService.delete(commentId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
