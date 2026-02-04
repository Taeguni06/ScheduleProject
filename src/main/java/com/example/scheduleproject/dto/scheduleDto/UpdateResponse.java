package com.example.scheduleproject.dto.scheduleDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime modifiedAt;

    public  UpdateResponse(Long id, String title, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.modifiedAt = modifiedAt;
    }
}
