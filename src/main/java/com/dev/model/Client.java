package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class Client extends AbstractPerson {

    private String businessArea;

    public Client() {
        super(EntityIdentifier.CLIENT);
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}
