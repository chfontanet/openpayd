package com.openpayd.transactions.persistence.model;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * Model class for Client
 */
@Entity
@Table(name = "client")
public class Client {

    /**
     * The generated id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The surname
     */
    @Column(name = "surname", nullable = false)
    private String surname;

    /**
     * The primary address. Referenced in Address class
     */
    @JoinColumn(name = "primary_address", nullable = false)
    @OneToOne
    private Address primaryAddress;

    /**
     * The secondary address. Referenced in Address class
     */
    @JoinColumn(name = "secondary_address")
    @OneToOne
    private Address secondaryAddress;

    public Long getId() {
        return id;
    }

    public Client setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(final String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Client setSurname(final String surname) {
        this.surname = surname;
        return this;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public Client setPrimaryAddress(final Address primaryAddress) {
        this.primaryAddress = primaryAddress;
        return this;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public Client setSecondaryAddress(final Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", primaryAddress=" + primaryAddress +
                ", secondaryAddress=" + secondaryAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equal(id, client.id) &&
                Objects.equal(name, client.name) &&
                Objects.equal(surname, client.surname) &&
                Objects.equal(primaryAddress, client.primaryAddress) &&
                Objects.equal(secondaryAddress, client.secondaryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, surname, primaryAddress, secondaryAddress);
    }
}
