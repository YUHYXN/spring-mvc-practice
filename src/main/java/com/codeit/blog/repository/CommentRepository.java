package com.codeit.blog.repository;
import com.codeit.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository {

    // 단일 조회
    Optional<Comment> findById(Long id);    // null을 반환 받을수도 있고 뭘 받을지 모르니 옵셔널로 감싸준다.


    // 댓글 저장
    Comment save(Comment comment);


    // 댓글 삭제
    void deleteById(Long id);


    // 게시물 번호로 댓글 조회
    List<Comment> findByPostId(Long postId);

}
