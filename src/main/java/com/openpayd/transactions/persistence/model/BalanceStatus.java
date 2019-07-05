package com.openpayd.transactions.persistence.model;

/**
 * Enum for the balance status in account
 */
public enum BalanceStatus {

    DR("debit"),
    CR("credit");

    private String name;

    BalanceStatus(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
