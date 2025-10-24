package com.inovabook.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file, String subfolder);

    boolean deleteFile(String subfolder, String filename);
}
