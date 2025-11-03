package com.inovabook.web.service.impl;

import com.inovabook.web.exception.FileStorageException;
import com.inovabook.web.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final Path ROOT_UPLOAD_PATH = Paths.get("uploads");

    @Override
    public String storeFile(MultipartFile file, String subfolder) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
        Path uploadPath = ROOT_UPLOAD_PATH.resolve(subfolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = StringUtils.cleanPath(
                Objects.requireNonNull(file.getOriginalFilename())
        );
        String filename = resolveUniqueFilename(uploadPath, originalFilename);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(filename));
        }
        return filename;
    } catch (IOException e) {
        throw new FileStorageException("Error storing file", e);}
    }

    @Override
    public boolean deleteFile(String subfolder, String filename) {
        if (filename == null || filename.isBlank()) return false;

        Path filePath = ROOT_UPLOAD_PATH.resolve(subfolder).resolve(filename);
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            // Log the error instead of throwing
            System.err.println("Failed to delete file: " + filePath + " — " + e.getMessage());
            return false;
        }
    }

    @Override
    public String replaceFile(String subfolder, String oldFilename, MultipartFile newFile) {
        if (newFile == null || newFile.isEmpty()) {
            return oldFilename;
        }
        if (oldFilename != null && !oldFilename.isBlank()) {
            // log failure and continue operation
            deleteFile(subfolder, oldFilename);
        }
        return storeFile(newFile, subfolder);
    }


    private String resolveUniqueFilename(Path uploadPath, String originalFilename) {
        String filename = originalFilename;
        Path filePath = uploadPath.resolve(filename);
        int counter = 1;

        while (Files.exists(filePath)) {
            int dotIndex = originalFilename.lastIndexOf(".");
            String base = (dotIndex > 0)
                    ? originalFilename.substring(0, dotIndex)
                    : originalFilename;
            String ext = (dotIndex > 0)
                    ? originalFilename.substring(dotIndex)
                    : "";

            filename = base + "(" + counter + ")" + ext;
            filePath = uploadPath.resolve(filename);
            counter++;
        }
        return filename;
    }
}

