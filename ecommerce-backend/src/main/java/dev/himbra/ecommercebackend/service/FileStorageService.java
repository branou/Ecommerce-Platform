package dev.himbra.ecommercebackend.service;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    //Resource loadFileAsResource(String fileName);
}
