package com.example.scheduleproject.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private String name;
    private String password;
}
