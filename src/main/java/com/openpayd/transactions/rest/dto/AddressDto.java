package com.openpayd.transactions.rest.dto;

import com.google.common.base.Objects;

import javax.validation.constraints.NotNull;

/**
 * The data transfer object for address
 */
public class AddressDto {

    /**
     * The address line 1
     */
    @NotNull
    private String addressLine1;
    /**
     * The address line 2
     */
    private String addressLine2;
    /**
     * The city
     */
    @NotNull
    private String city;
    /**
     * The country
     */
    @NotNull
    private String country;

    public String getAddressLine1() {
        return addressLine1;
    }

    public AddressDto setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public AddressDto setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(final String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressDto setCountry(final String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equal(addressLine1, that.addressLine1) &&
                Objects.equal(addressLine2, that.addressLine2) &&
                Objects.equal(city, that.city) &&
                Objects.equal(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addressLine1, addressLine2, city, country);
    }
}
