package com.codeit.blog.dto.request;

import com.codeit.blog.entity.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostRequest {

    private String title;
    private String content;
    private String author;
    private Category category;
    private String thumbnailPath;



}
