package com.example.scheduleproject.dto.commentDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    @Column(length = 100, nullable = false)
    @Size(max = 100, message = "최대 100자입니다.")
    @NotBlank(message = "필수 항목입니다.")
    private String content;
    @NotBlank(message = "필수 항목입니다.")
    private String password;
}
