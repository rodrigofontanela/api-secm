package com.flamel.documents.repository;

import com.flamel.documents.model.Document;

import java.util.Optional;

public interface DocumentRepository extends AbstractRepository<Document, Long> {

    Optional<Document> findByTitle(String title);
}
