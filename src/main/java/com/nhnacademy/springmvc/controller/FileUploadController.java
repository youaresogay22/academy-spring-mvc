package com.nhnacademy.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {
    @PostMapping("/fileUpload")
    public String processUpload(@RequestParam("file") MultipartFile file,
                                @Value("${upload.dir}") String uploadDir,
                                Model model) throws IOException {
        file.transferTo(Paths.get(uploadDir + file.getOriginalFilename()));

        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("size", file.getSize());

        return "uploadSuccess";
    }
}
