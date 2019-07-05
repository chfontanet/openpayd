package com.openpayd.transactions.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * The data transfer object for account
 */
public class AccountDto {

    /**
     * the id, readonly
     */
    @JsonProperty(access = READ_ONLY)
    private Long id;
    /**
     * the type
     */
    @NotNull
    private String type;
    /**
     * The balance
     */
    @NotNull
    private Double balance;
    /**
     * The balance status
     */
    @NotNull
    private String balanceStatus;
    /**
     * The date created, readonly
     */
    @JsonProperty(access = READ_ONLY)
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public AccountDto setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public AccountDto setType(final String type) {
        this.type = type;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public AccountDto setBalance(final Double balance) {
        this.balance = balance;
        return this;
    }

    public String getBalanceStatus() {
        return balanceStatus;
    }

    public AccountDto setBalanceStatus(final String balanceStatus) {
        this.balanceStatus = balanceStatus;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public AccountDto setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", balanceStatus='" + balanceStatus + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(type, that.type) &&
                Objects.equal(balance, that.balance) &&
                Objects.equal(balanceStatus, that.balanceStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, type, balance, balanceStatus);
    }
}
