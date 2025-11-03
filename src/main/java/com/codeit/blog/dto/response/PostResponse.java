package com.codeit.blog.dto.response;

import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private int viewCount;
    private String thumbnailPath;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .category(post.getCategory())
                .viewCount(post.getViewCount())
                .thumbnailPath(post.getThumbnailPath())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build();
    }

}