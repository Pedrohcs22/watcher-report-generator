package com.dev.model;

import com.dev.model.enumeration.PersonType;

public class AbstractPerson {

    private String name;

    private String identifier;

    private PersonType personType;

    public AbstractPerson(String name, String identifier, PersonType personType) {
        this.name = name;
        this.identifier = identifier;
        this.personType = personType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}
