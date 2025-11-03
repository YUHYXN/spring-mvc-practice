package com.codeit.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // yml에 작성 해놓고 @Value 등으로 불러와도 된다.
    private final String uploadDir;

    public FileService(@Value("${blog.file-directory}") String uploadDir) {

        this.uploadDir = uploadDir;
        File dir = new File(uploadDir);
        if (!dir.exists()) {    // 만약 경로가 존재하지 않는다면
            dir.mkdirs();     // 경로에 해당하는 디렉토리를 생성한다.
        }
    }

    public String saveFile(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        // 원판 파일명에서 확장자 추출
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // UUID를 활용하여 고유한 파일명 생성
        String saveFileName
                = UUID.randomUUID().toString().replaceAll("-", "") + extension;
        // UUID에서 하이픈을 제거 하겠다.


        // 파일 저장
        File uploadPath = new File(uploadDir, saveFileName);
        try {
            file.transferTo(uploadPath); // 실제로 파일을 저장하는 메서드
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }

        return saveFileName;

    }

    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) return;

        File file = new File(uploadDir + "/" + fileName);
        if (file.exists()) {
            file.delete();
        }

    }


}
