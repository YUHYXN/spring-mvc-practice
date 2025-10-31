package com.codeit.blog.repository;

import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryPostRepository implements PostRepository {

    private final Map<Long, Post> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);


    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            post.setId(sequence.getAndIncrement());
        }
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Post> findByCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Post> findByTitleContaining(String keyword) {
        return List.of();
    }

    @Override
    public List<Post> findByTitleOrContentContaining(String keyword) {
        return List.of();
    }
}
