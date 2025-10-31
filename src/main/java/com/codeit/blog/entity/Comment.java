package com.codeit.blog.entity;

import java.time.LocalDateTime;

public class Comment {

    private Long id;
    private Long postId;
    private String content;
    private String author;
    private LocalDateTime createdAt;

}
