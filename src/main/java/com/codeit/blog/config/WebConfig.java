package com.codeit.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${blog.file-directory}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 화면단에서 img 태그가 /images/..로 시작하는 url 요청 ->
        // uploadDir에 저장된 로컬 경로에서 파일을 제공.
        // 이런 짓을 왜 해야 하지? -> 브라우저에서는 특정 컴퓨터의 로컬 환경으로 직접 접근 자체가 막혀 있어요.
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadDir);
    }
}