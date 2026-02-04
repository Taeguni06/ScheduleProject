package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "필수 항목입니다.")
    @Size(max = 30, message = "최대 30자 이하입니다.")
    @Column(length = 30, nullable = false)
    private String title;
    @NotBlank(message = "필수 항목입니다.")
    @Size(max = 200, message = "최대 200자 이하입니다.")
    @Column(length = 200, nullable = false)
    private String content;
    @NotBlank(message = "필수 항목입니다.")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "필수 항목입니다.")
    @Column(nullable = false)
    private String password;

    public Schedule(String title, String content, String name, String password) {
        this.title = title;
        this.content = content;
        this.name = name;
        this.password = password;
    }

    public void update(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
