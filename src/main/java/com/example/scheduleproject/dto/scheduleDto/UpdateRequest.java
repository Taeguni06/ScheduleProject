package com.example.scheduleproject.dto.scheduleDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateRequest {
    @Column(nullable = false, length = 30)
    @Size(max = 30, message = "최대 30자 이하입니다.")
    @NotBlank(message = "필수 항목입니다.")
    private String title;
    @Column(nullable = false)
    @NotBlank(message = "필수 항목입니다.")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "필수 항목입니다.")
    private String password;
}
