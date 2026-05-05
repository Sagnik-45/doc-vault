package com.example.docVault.repository;
import com.example.docVault.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface DocumentRepository extends JpaRepository<Document, Long>{
    List<Document> findByCategoryIgnoreCase(String category);

    List<Document> findByOriginalNameContainingIgnoreCase(String keyword);

    @Query("SELECT d FROM Document d JOIN d.tags t WHERE t = :tag")
    List<Document> findByTag(@Param("tag") String tag);
}
