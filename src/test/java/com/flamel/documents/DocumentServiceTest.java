package com.flamel.documents;

import com.flamel.documents.model.Document;
import com.flamel.documents.repository.DocumentRepository;
import com.flamel.documents.service.DocumentService;
import com.flamel.documents.service.exception.DocumentAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class DocumentServiceTest {

    private DocumentService documentService;

    @Mock
    private DocumentRepository documentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        documentService = new DocumentService(documentRepository);
    }

    @Test(expected = DocumentAlreadyExistsException.class)
    public void shouldDenyCreationOfDocumentThatExists() {

        when(documentRepository.findByTitle("My document 1"))
                .thenReturn(Optional.of(Document.builder().title("My document 1").build()));

        documentService.save(Document.builder().title("My document 1").build());
    }

    @Test
    public void shoudCreateNewBeer() {

        final Document newDocument = Document.builder().title("My document 1").build();
        final Document newDocumentInDatabase = Document.builder().title("My document 1").build();

        when(documentRepository.save(newDocument)).thenReturn(newDocumentInDatabase);

        final Document document = documentService.save(newDocument);

        assertThat(document.getTitle(), equalTo("My document 1"));
    }
}
