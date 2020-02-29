package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class AbstractPerson extends AbstractEntity {

    private String name;

    private String documentNumber;

    public AbstractPerson(EntityIdentifier entityIdentifier) {
        super(entityIdentifier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
