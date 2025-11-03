package com.codeit.blog.service;

import com.codeit.blog.dto.request.PostRequest;
import com.codeit.blog.dto.response.PostResponse;
import com.codeit.blog.entity.Category;
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
//        return postRepository.findAll().stream()
//                .sorted(Comparator.comparing(Post::getCreateAt).reversed()) // 생성일자 내림차 정렬
//                .collect(Collectors.toList());
            List<Post> posts = postRepository.findAll();
            posts.sort((a, b) -> {
                var ca = a.getCreateAt();
                var cb = b.getCreateAt();
                if (ca == null && cb == null) return 0; // 둘 다 null
                if (ca == null) return 1;               // null은 뒤로
                if (cb == null) return -1;              // null은 뒤로
                return cb.compareTo(ca);                // 내림차순(최신 우선)
            });
            return posts;
    }

    public Post createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .content(request.getContent())
                .category(request.getCategory())
                .thumbnailPath(request.getThumbnailPath())
                .viewCount(0)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();
        return postRepository.save(post);
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        postRepository.updateViewCount(id);
        return PostResponse.from(post);
    }

    public List<PostResponse> searchPost(String keyword, Category category, String sort) {  // posts 리스폰스로 바꿔서 리턴해야한다 (Map)
        List<Post> posts;

        if (category != null) {
            posts = postRepository.findByCategory(category);
        } else if (keyword != null && !keyword.trim().isEmpty()) {
            posts = postRepository.findByTitleOrContentContaining(keyword);
        }else  {
            posts = postRepository.findAll();
        }

        // 정렬
        if ("viewCount".equals(sort)) {
            posts.stream()
                    .sorted(Comparator.comparing(Post::getViewCount).reversed())
                    .collect(Collectors.toList());
        }else  {
            posts.sort(Comparator.comparing(Post::getViewCount).reversed());
        }   // Map이나 DTO로 바꿔서 리턴해야함

        return posts.stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());


    }
}






