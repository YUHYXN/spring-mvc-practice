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
import java.util.stream.Collectors;

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
        return store.values().stream()
                .filter(post -> post.getCategory() == category)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByTitleContaining(String keyword) {
        return List.of();
    }

    @Override
    // toUpperCase() 이건 대문자로 바꿔서 비교
    // toLowerCase() 이건 소문자로 바꿔서 비교
    public List<Post> findByTitleOrContentContaining(String keyword) {
        return store.values().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        post.getContent().contains(keyword))
                .collect(Collectors.toList());


        // 위 코드는 어떤 뜻이냐면
        // store 맵에 있는 모든 게시글들을 순회하면서
        // 각 게시글의 제목이나 내용에 keyword가 포함되어 있는지 확인하고
        // 포함되어 있으면 그 게시글을 결과 리스트에 추가하는 거야.

    }

    @Override
    public void updateViewCount(Long id) {
        store.get(id).setViewcount();
    }
}
