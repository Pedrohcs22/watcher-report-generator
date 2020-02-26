package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class AbstractPerson {

    private String name;

    private EntityIdentifier identifier;

    private String documentNumber;

    public AbstractPerson(EntityIdentifier entityIdentifier) {
        this.identifier = entityIdentifier;
    }

    public AbstractPerson(String name, EntityIdentifier identifier, String documentNumber) {
        this.name = name;
        this.identifier = identifier;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(EntityIdentifier identifier) {
        this.identifier = identifier;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
