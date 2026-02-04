package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")

public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long scheduleId;
    @NotBlank(message = "필수 항목입니다.")
    @Size(max = 100, message = "최대 100자 이하입니다.")
    @Column(length = 100, nullable = false)
    private String content;
    @NotBlank(message = "필수 입력 항목입니다.")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "필수 입력 항목입니다.")
    @Column(nullable = false)
    private String password;

    public Comment(Long scheduleId, String content, String name, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    public void update(Long scheduleId, String content, String name, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.name = name;
        this.password = password;
    }
}
