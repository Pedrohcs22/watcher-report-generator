package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class AbstractEntity {
    private EntityIdentifier identifier;

    public AbstractEntity(EntityIdentifier identifier) {
        this.identifier = identifier;
    }

    public EntityIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(EntityIdentifier identifier) {
        this.identifier = identifier;
    }
}
