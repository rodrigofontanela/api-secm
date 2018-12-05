package com.flamel.documents.model;

public enum Status {
    CADASTRADO(0, "Solicitado"),
    APROVADO(1, "Aprovado"),
    REJEITADO(2, "Rejeitado");

    private Integer value;
    private String description;

    Status(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
