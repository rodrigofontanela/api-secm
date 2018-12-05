package com.flamel.documents.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Document {

    @Id
    @SequenceGenerator(name = "DOCUMENT_SEQUENCE", sequenceName = "DOCUMENT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQUENCE")
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @Size(max = 255, message = "generic-invalid-length")
    @NotBlank(message = "generic-not-null")
    private String title;

    @Column
    @Size(max = 3, message = "generic-invalid-length")
    private String description;

    @Column
    @NotNull(message = "document-30")
    private Integer status = Status.CADASTRADO.getValue();
}
