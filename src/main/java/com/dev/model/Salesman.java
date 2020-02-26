package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class Salesman extends AbstractPerson {

    private float salary;

    public Salesman() {
        super(EntityIdentifier.SALESMAN);
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
