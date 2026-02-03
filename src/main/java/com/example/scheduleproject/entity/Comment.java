package com.example.scheduleproject.entity;

import jakarta.persistence.*;
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
    @Column(length = 100, nullable = false)
    private String content;
    @Column(nullable = false)
    private String name;
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
