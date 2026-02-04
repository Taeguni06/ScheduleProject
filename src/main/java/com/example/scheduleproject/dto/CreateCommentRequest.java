package com.example.scheduleproject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @Column(length = 100, nullable = false)
    @Size(max = 100, message = "최대 100자입니다.")
    @NotBlank(message = "필수 항목입니다.")
    private String content;
    @Column(nullable = false)
    @NotBlank(message = "필수 항목입니다.")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "필수 항목입니다.")
    private String password;
}
