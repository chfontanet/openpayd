package com.openpayd.transactions.persistence.model;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * Model class for Address
 */
@Entity
@Table(name = "address")
public class Address {

    /**
     * The generated id
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The address line 1
     */
    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    /**
     * The address line 2
     */
    @Column(name = "address_line_2")
    private String addressLine2;

    /**
     * The city
     */
    @Column(name = "city", nullable = false)
    private String city;

    /**
     * The country
     */
    @Column(name = "country", nullable = false)
    private String country;

    public Long getId() {
        return id;
    }

    public Address setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public Address setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public Address setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(final String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Address setCountry(final String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equal(id, address.id) &&
                Objects.equal(addressLine1, address.addressLine1) &&
                Objects.equal(addressLine2, address.addressLine2) &&
                Objects.equal(city, address.city) &&
                Objects.equal(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, addressLine1, addressLine2, city, country);
    }
}
