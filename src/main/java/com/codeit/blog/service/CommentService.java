package com.codeit.blog.service;

import com.codeit.blog.dto.response.CommentResponse;
import com.codeit.blog.entity.Comment;
import com.codeit.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentResponse> getCommentsByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        return commentList.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
    }

}