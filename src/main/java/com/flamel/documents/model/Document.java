package com.flamel.documents.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.flamel.documents.model.definitions.EntityDefaults.Columns.Validations.DEFAULT_SIZE_VALIDATION_MESSAGE;

@Entity(name = "DOCUMENT")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Document {

    @Id
    @SequenceGenerator(name = "DOCUMENT_SEQUENCE", sequenceName = "DOCUMENT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQUENCE")
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @Size(max = 255, message = DEFAULT_SIZE_VALIDATION_MESSAGE)
    @NotNull
    private String title;

    @Column
    @Size(max = 1024, message = DEFAULT_SIZE_VALIDATION_MESSAGE)
    private String description;

    @Column
    @NotNull
    private Integer status = Status.CADASTRADO.getValue();

}
