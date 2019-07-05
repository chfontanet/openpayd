package com.openpayd.transactions.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * The data transfer object for transaction
 */
public class TransactionDto {

    /**
     * The id, readonly
     */
    @JsonProperty(access = READ_ONLY)
    private Long id;
    /**
     * The debit account
     */
    @NotNull
    private Long debitAccount;
    /**
     * The credit account
     */
    @NotNull
    private Long creditAccount;
    /**
     * The amount
     */
    @NotNull
    private Double amount;
    /**
     * The message
     */
    private String message;
    /**
     * The date created, readonly
     */
    @JsonProperty(access = READ_ONLY)
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public TransactionDto setId(final Long id) {
        this.id = id;
        return this;
    }

    public Long getDebitAccount() {
        return debitAccount;
    }

    public TransactionDto setDebitAccount(final Long debitAccount) {
        this.debitAccount = debitAccount;
        return this;
    }

    public Long getCreditAccount() {
        return creditAccount;
    }

    public TransactionDto setCreditAccount(final Long creditAccount) {
        this.creditAccount = creditAccount;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionDto setAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TransactionDto setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public TransactionDto setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", debitAccount=" + debitAccount +
                ", creditAccount=" + creditAccount +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(debitAccount, that.debitAccount) &&
                Objects.equal(creditAccount, that.creditAccount) &&
                Objects.equal(amount, that.amount) &&
                Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, debitAccount, creditAccount, amount, message);
    }
}
