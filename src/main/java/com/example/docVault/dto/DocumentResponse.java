package com.example.docVault.dto;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {
    private Long id;
    private String originalName;
    private String contentType;
    private Long fileSize;
    private String category;
    private List<String> tags;
    private LocalDateTime uploadedAt;
}
