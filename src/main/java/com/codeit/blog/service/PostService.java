package com.codeit.blog.service;

import com.codeit.blog.dto.request.PostRequest;
import com.codeit.blog.entity.Post;
import com.codeit.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll().stream()
                .sorted(Comparator.comparing(Post::getCreateAt).reversed()) // 생성일자 내림차 정렬
                .collect(Collectors.toList());
    }

    public Post createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .content(request.getContent())
                .category(request.getCategory())
                .viewcount(0)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        return postRepository.save(post);
    }
}






