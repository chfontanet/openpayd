package com.openpayd.transactions.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * The data transfer object for client
 */
public class ClientDto {

    /**
     * The id, readonly
     */
    @JsonProperty(access = READ_ONLY)
    private Long id;
    /**
     * The name
     */
    @NotNull
    private String name;
    /**
     * The surname
     */
    @NotNull
    private String surname;
    /**
     * The primary address
     */
    @NotNull
    @Valid
    private AddressDto primaryAddress;
    /**
     * The secondary accress
     */
    @Valid
    private AddressDto secondaryAddress;

    public Long getId() {
        return id;
    }

    public ClientDto setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientDto setName(final String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public ClientDto setSurname(final String surname) {
        this.surname = surname;
        return this;
    }

    public AddressDto getPrimaryAddress() {
        return primaryAddress;
    }

    public ClientDto setPrimaryAddress(final AddressDto primaryAddress) {
        this.primaryAddress = primaryAddress;
        return this;
    }

    public AddressDto getSecondaryAddress() {
        return secondaryAddress;
    }

    public ClientDto setSecondaryAddress(final AddressDto secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
        return this;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
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
        ClientDto clientDto = (ClientDto) o;
        return Objects.equal(id, clientDto.id) &&
                Objects.equal(name, clientDto.name) &&
                Objects.equal(surname, clientDto.surname) &&
                Objects.equal(primaryAddress, clientDto.primaryAddress) &&
                Objects.equal(secondaryAddress, clientDto.secondaryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, surname, primaryAddress, secondaryAddress);
    }
}
