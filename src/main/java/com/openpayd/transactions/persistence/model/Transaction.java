package com.openpayd.transactions.persistence.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

/**
 * Model class for Transaction
 */
@Entity
@Table(name = "transaction")
public class Transaction {

    /**
     * The generated id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The debit account
     */
    @Column(name = "debit_account", nullable = false)
    @JoinColumn(table = "account", name = "id")
    private Long debitAccount;

    /**
     * The credit account
     */
    @Column(name = "credit_account", nullable = false)
    @JoinColumn(table = "account", name = "id")
    private Long creditAccount;

    /**
     * The amount
     */
    @Column(name = "amount", nullable = false)
    private Double amount;

    /**
     * The message
     */
    @Column(name = "message")
    private String message;

    /**
     * The generated date created
     */
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public Transaction setId(final Long id) {
        this.id = id;
        return this;
    }

    public Long getDebitAccount() {
        return debitAccount;
    }

    public Transaction setDebitAccount(final Long debitAccount) {
        this.debitAccount = debitAccount;
        return this;
    }

    public Long getCreditAccount() {
        return creditAccount;
    }

    public Transaction setCreditAccount(final Long creditAccount) {
        this.creditAccount = creditAccount;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Transaction setMessage(final String message) {
        this.message = message;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Transaction setDateCreated(final Date dateCreated) {
        this.dateCreated = new Date();
        return this;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Date();
    }

    @Override
    public String toString() {
        return "Transaction{" +
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
        Transaction that = (Transaction) o;
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
