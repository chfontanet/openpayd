package com.openpayd.transactions.persistence.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

/**
 * Model class for Account
 */
@Entity
@Table(name = "account")
public class Account {

    /**
     * The generated id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Column to make a relation between accounts and clients
     */
    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne
    private Client client;

    /**
     * The type. Defined in an enum
     */
    @Column(name = "type", nullable = false)
    private Type type;

    /**
     * The balance
     */
    @Column(name = "balance", nullable = false)
    private Double balance;

    /**
     * The balance status. Defined in an enum
     */
    @Column(name = "balance_status", nullable = false)
    private BalanceStatus balanceStatus;

    /**
     * The generated date created
     */
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public Account setId(final Long id) {
        this.id = id;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Account setClient(final Client client) {
        this.client = client;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Account setType(final Type type) {
        this.type = type;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Account setBalance(final Double balance) {
        this.balance = balance;
        return this;
    }

    public BalanceStatus getBalanceStatus() {
        return balanceStatus;
    }

    public Account setBalanceStatus(final BalanceStatus balanceStatus) {
        this.balanceStatus = balanceStatus;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Account setDateCreated(final Date dateCreated) {
        this.dateCreated = new Date();
        return this;
    }

    @PrePersist
    void dateCreated() {
        this.dateCreated = new Date();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", type=" + type +
                ", balance=" + balance +
                ", balanceStatus=" + balanceStatus +
                ", dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equal(id, account.id) &&
                Objects.equal(client, account.client) &&
                type == account.type &&
                Objects.equal(balance, account.balance) &&
                balanceStatus == account.balanceStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, client, type, balance, balanceStatus);
    }
}
