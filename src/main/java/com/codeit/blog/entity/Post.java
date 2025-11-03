package com.codeit.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
// @Setter -> Entity에서 setter 사용은 권장하지 않는다.
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Category category;
    private  int viewcount;
    private String thumbnailPath;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    public Post() {
        this.viewcount = 0;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }


    public Post(String title, String content, String author, Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public void setViewcount() {
        this.viewcount++;
    }
}
