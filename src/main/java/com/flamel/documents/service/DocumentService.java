package com.flamel.documents.service;

import com.flamel.documents.model.Document;
import com.flamel.documents.repository.DocumentRepository;
import com.flamel.documents.service.exception.DocumentAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    public DocumentService(@Autowired DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document save(final Document document) {
        final Optional<Document> documentByTitle = documentRepository.findByTitle(document.getTitle());

        if (documentByTitle.isPresent()) {
            throw new DocumentAlreadyExistsException();
        }

        return documentRepository.save(document);
    }
}
