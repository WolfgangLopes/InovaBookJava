package com.inovabook.web.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileStorageService {

    String storeFile(MultipartFile file, String subfolder) throws IOException;

    boolean deleteFile(String subfolder, String filename);
}
