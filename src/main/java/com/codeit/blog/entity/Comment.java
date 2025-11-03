package com.codeit.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    private Long id;
    private Long postId;
    private String content;
    private String author;
    private LocalDateTime createAt;

}
