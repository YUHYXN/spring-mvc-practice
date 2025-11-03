package com.codeit.blog.dto.response;

import com.codeit.blog.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime createAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .createAt(comment.getCreateAt())
                .build();
    }


}