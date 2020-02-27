package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

import java.math.BigDecimal;

public class Salesman extends AbstractPerson {

    private BigDecimal salary;

    public Salesman() {
        super(EntityIdentifier.SALESMAN);
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
