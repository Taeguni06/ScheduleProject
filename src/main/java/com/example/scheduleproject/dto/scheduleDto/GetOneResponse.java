package com.example.scheduleproject.dto.scheduleDto;

import com.example.scheduleproject.dto.commentDto.GetCommentResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOneResponse {
    private final GetResponse getResponse;
    private final List<GetCommentResponse> comments;

    public GetOneResponse(GetResponse getResponse, List<GetCommentResponse> comments) {
        this.getResponse = getResponse;
        this.comments = comments;
    }
}
