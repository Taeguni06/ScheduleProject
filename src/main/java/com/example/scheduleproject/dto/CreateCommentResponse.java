package com.example.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final Long scheduleId;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateCommentResponse(
            Long id,
            Long scheduleId,
            String content,
            String name,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
