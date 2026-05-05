package com.example.docVault.service;
import com.example.docVault.dto.DocumentResponse;
import com.example.docVault.exception.ResourceNotFoundException;
import com.example.docVault.model.Document;
import com.example.docVault.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public DocumentResponse upload(MultipartFile file, String category, List<String> tags) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String storedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Document doc = new Document();
        doc.setOriginalName(file.getOriginalFilename());
        doc.setStoredName(storedName);
        doc.setContentType(file.getContentType());
        doc.setFileSize(file.getSize());
        doc.setCategory(category);
        doc.setTags(tags);

        return toResponse(repository.save(doc));
    }

    public List<DocumentResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<DocumentResponse> search(String keyword) {
        return repository.findByOriginalNameContainingIgnoreCase(keyword)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<DocumentResponse> getByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public List<DocumentResponse> getByTag(String tag) {
        return repository.findByTag(tag)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public Resource download(Long id) throws MalformedURLException {
        Document doc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        Path filePath = Paths.get(uploadDir).resolve(doc.getStoredName());
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) throw new ResourceNotFoundException("File not found on disk");
        return resource;
    }

    public void delete(Long id) throws IOException {
        Document doc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
        Files.deleteIfExists(Paths.get(uploadDir).resolve(doc.getStoredName()));
        repository.delete(doc);
    }

    private DocumentResponse toResponse(Document doc) {
        return DocumentResponse.builder()
                .id(doc.getId())
                .originalName(doc.getOriginalName())
                .contentType(doc.getContentType())
                .fileSize(doc.getFileSize())
                .category(doc.getCategory())
                .tags(doc.getTags())
                .uploadedAt(doc.getUploadedAt())
                .build();
    }
}

