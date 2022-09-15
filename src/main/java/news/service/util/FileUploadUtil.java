package news.service.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {
    private final static String PATH_TO_IMAGE = System.getProperty("java.io.tmpdir");

    public static URI saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(PATH_TO_IMAGE);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        byte[] fileBytes = multipartFile.getBytes();
        Files.write(filePath, fileBytes);
        return filePath.toUri();
    }
}
