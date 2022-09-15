package news.controller;

import news.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService service;
    @PostMapping("/upload")
    public URI attachFiles(@RequestParam MultipartFile file) throws IOException {
       return service.uploadImage(file);
    }
}
