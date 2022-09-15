package news.service;

import news.service.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Service
public class PictureService {

    public URI uploadImage(MultipartFile file) {
        return FileUploadUtil.saveFile(file.getOriginalFilename(), file);
    }
}
