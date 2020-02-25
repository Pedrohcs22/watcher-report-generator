package com.dev.model;

import com.dev.model.enumeration.PersonType;

public class Client extends AbstractPerson {

    private String businessArea;

    public Client(String name, String identifier, PersonType personType, String businessArea) {
        super(name, identifier, personType);
        this.businessArea = businessArea;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }
}
