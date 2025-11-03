package com.codeit.blog.repository;

import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {

     List<Post> findAll();

     Optional<Post> findById(Long id);

     Post save(Post post);

     void deleteById(Long id);

     List<Post> findByCategory(Category category);
     List<Post> findByTitleContaining(String keyword);
     List<Post> findByTitleOrContentContaining(String keyword);


     void updateViewCount(Long id);


}
