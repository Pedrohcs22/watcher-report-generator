package com.dev.model;

import com.dev.model.enumeration.PersonType;

public class Salesman extends AbstractPerson {

    private float salary;

    public Salesman(String name, String identifier, PersonType personType, float salary) {
        super(name, identifier, personType);
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
