package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.commentDto.*;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.global.exception.NotEqualsPasswordException;
import com.example.scheduleproject.global.exception.NotFoundException;
import com.example.scheduleproject.repository.CommentRepository;
import com.example.scheduleproject.global.exception.LimitCommentException;
import jakarta.validation.Valid;
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
        if (commentRepository.countByScheduleId(sId) >= 10) {
            throw new LimitCommentException("오류: 댓글 갯수 초과 (최대 10개)");
        }

        Comment comment = new Comment(
                sId,
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
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

    @Transactional
    public void delete(Long commentId, DeleteCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("오류: 존재하지 않음")
        );

        if (!request.getPassword().equals(comment.getPassword())) {
            throw new NotEqualsPasswordException("오류: 비밀번호 불일치");
        }

        commentRepository.deleteById(commentId);
    }

    public UpdateCommentResponse update(@Valid UpdateCommentRequest request, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("오류: 존재하지 않음")
        );

        if (!request.getPassword().equals(comment.getPassword())) {
            throw new NotEqualsPasswordException("오류: 비밀번호 불일치");
        }

        comment.update(
                request.getContent()
        );

        return new UpdateCommentResponse(
                comment.getContent(),
                comment.getName(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
