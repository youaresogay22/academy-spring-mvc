package com.nhnacademy.springmvc.controller;

import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// TODO #6: 파일 업로드 처리
@Controller
public class FileUploadController {
    private static final String UPLOAD_DIR = "/Users/user/Downloads/";

    @PostMapping("/fileUpload")
    public String processUpload(@RequestParam("file") MultipartFile file,
                                Model model) throws IOException {
        file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));

        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("size", file.getSize());

        return "uploadSuccess";
    }
}
