package com.codeit.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController // ImageController는 특정 html 페이지를 응답하는 게 아니라 이미지만 클라이언트로 던지는 역할
@RequestMapping("/images")
public class ImgController {

    @Value("${blog.file-directory}")
    private String uploadDir;

    @GetMapping("/{fileName}")  // html의 image 태그의 요청 방식은 다 get 방식
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        File file = new File(uploadDir + "/" + fileName);
        ResponseEntity<byte[]> result = null;
        HttpHeaders headers = new HttpHeaders();    // 응답용 헤더 객체 생성

        try {
            // 브라우저에게 응답하는 컨텐츠의 타입이 무엇인지를 명시해줌 -> 그래야 브라우저가 명확하게 화면에 표시하는 게 가능해진다.
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            // 전달하고자 하는 파일을 카피한 후 바이트 배열로 변환해서 ResponseEntity 객체에 담아 전달.
            byte[] bytes = FileCopyUtils.copyToByteArray(file);
            result = new ResponseEntity<>(bytes, headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("파일을 불러오는 데 실패했습니다.");
        }

        return result;
    }

}
