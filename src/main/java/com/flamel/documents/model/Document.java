package com.flamel.documents.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Document {

    @Id
    @SequenceGenerator(name = "DOCUMENT_SEQUENCE", sequenceName = "DOCUMENT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQUENCE")
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @Size(max = 255, message = "DOC-010")
    @NotBlank(message = "DOC-011")
    private String title;

    @Column
    @Size(max = 3, message = "DOC-020")
    private String description;

    @Column
    @NotNull
    private Status status = Status.CADASTRADO;
}
