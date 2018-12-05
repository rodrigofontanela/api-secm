package com.flamel.documents.resource;

import com.flamel.documents.model.Document;
import com.flamel.documents.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentResource {

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping
    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Document create(@RequestBody Document document) {
        return documentRepository.save(document);
    }
}
