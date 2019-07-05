package com.openpayd.transactions.persistence.model;

/**
 * Enum for type in account
 */
public enum Type {

    CURRENT("current"),
    SAVINGS("savings");

    private String name;

    Type(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
