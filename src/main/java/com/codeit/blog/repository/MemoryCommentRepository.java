package com.codeit.blog.repository;

import com.codeit.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemoryCommentRepository implements CommentRepository {

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return List.of();
    }
}
