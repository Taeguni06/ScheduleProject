package com.example.scheduleproject.dto.commentDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponse {
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public UpdateCommentResponse(String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
