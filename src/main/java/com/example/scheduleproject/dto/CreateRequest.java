package com.example.scheduleproject.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CreateRequest {
    private String title;
    private String content;
    private String name;
    private String password;
}
