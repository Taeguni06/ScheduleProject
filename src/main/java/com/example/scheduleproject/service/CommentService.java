package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.CreateCommentRequest;
import com.example.scheduleproject.dto.CreateCommentResponse;
import com.example.scheduleproject.dto.GetCommentResponse;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;


    @Transactional
    public CreateCommentResponse create(Long sId, CreateCommentRequest request) {
        Comment comment = new Comment(
                sId,
                request.getContent(),
                request.getName(),
                request.getPassword()
        );

        if (commentRepository.countByScheduleId(sId) >= 10) {
            throw new IllegalStateException("오류: 댓글 갯수 초과 (최대 10개)");
        }

        Comment save = commentRepository.save(comment);

        return new CreateCommentResponse(
                save.getId(),
                save.getScheduleId(),
                save.getContent(),
                save.getName(),
                save.getCreatedAt(),
                save.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetCommentResponse> findBySId(Long sId) {
        List<Comment> byScheduleId = commentRepository.findByScheduleId(sId);

        List<GetCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : byScheduleId) {
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getScheduleId(),
                    comment.getContent(),
                    comment.getName(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
