package com.codeit.blog.controller;

import com.codeit.blog.dto.request.PostRequest;
import com.codeit.blog.dto.response.CommentResponse;
import com.codeit.blog.dto.response.PostResponse;
import com.codeit.blog.entity.Category;
import com.codeit.blog.entity.Post;
import com.codeit.blog.service.CommentService;
import com.codeit.blog.service.FileService;
import com.codeit.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller // 컨트롤러가 요청에 맞는 뷰 페이지를 결정하게 하겠다.
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final FileService fileService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        System.out.println("/posts: GET, 목록 요청!");
        List<Post> allPosts = postService.getAllPosts();
        // model: 자바 데이터를 뷰(사용자에게 응답할 페이지)로 전달하는 용도로 사용하는 객체.
        model.addAttribute("posts", allPosts);
        model.addAttribute("pageTitle", "블로그에 오신것을 환영합니다!");
        return "posts/list";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("pageTitle", "✏️ 새 글 작성");
        model.addAttribute("categories", Category.values());
        return "posts/form";
    }

    @PostMapping
    public String create(PostRequest request,
                         // 만약 전달되는 파일이 여러개다? List<MultipartFile> 로 받으면 끝. 반복 처리.
                         @RequestParam(value = "thumbnail", required = false) MultipartFile file,
                         Model model) {
        System.out.println("/posts: POST, 글 등록 요청!");

        // 파일 업로드 처리
        if (file != null && !file.isEmpty()) {
            String fileName = fileService.saveFile(file);
            request.setThumbnailPath(fileName);
        }

        // form 태그에서 전송되는 데이터는 입력 양식 태그의 name 속성의 이름으로 얻어올 수 있습니다.
        Post saved = postService.createPost(request);

        // redirect: 재요청
        // "redirect:/posts" -> 응답을 클라이언트로 내보낸 후 자동 재요청으로 /posts 요청이 들어오도록 유도해 달라.
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        PostResponse dto = postService.getPostById(id);
        List<CommentResponse> comments = commentService.getCommentsByPostId(id);

        model.addAttribute("post", dto);
        model.addAttribute("pageTitle", dto.getTitle());
        model.addAttribute("comments", comments);

        return "posts/detail";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false, defaultValue = "latest") String sort,
            Model model
    ) {
        List<PostResponse> posts = postService.searchPost(keyword, category, sort);

        model.addAttribute("posts", posts);
        model.addAttribute("keyword",  keyword);
        model.addAttribute("category", category);
        model.addAttribute("sort", sort);
        model.addAttribute("pageTitle", "🔎 검색 결과");

        return "posts/list";
    }

    // 게시글 삭제 -> 게시글에 포함된 이미지 파일도 함께 삭제 (Post가 파일명을 가지고 있음.)

}








