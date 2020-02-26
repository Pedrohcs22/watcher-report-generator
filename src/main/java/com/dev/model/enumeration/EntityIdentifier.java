package com.dev.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum EntityIdentifier {
    SALESMAN(001),
    CLIENT(002),
    SALE(003);

    private int identifier;

    EntityIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public static Optional<EntityIdentifier> valueOf(int identifier) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.identifier == identifier)
                .findFirst();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}