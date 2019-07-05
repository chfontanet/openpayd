package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.persistence.model.Address;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.rest.dto.AddressDto;
import com.openpayd.transactions.rest.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

/**
 * Mapper class to convert between model and DTO
 */
@Component
public class ClientMapper {

    /**
     * Converts a client to a dto.
     *
     * @param client the client
     * @return a client dto
     */
    public ClientDto toDto(final Client client) {
        return new ClientDto()
                .setId(client.getId())
                .setName(client.getName())
                .setSurname(client.getSurname())
                .setPrimaryAddress(toDto(client.getPrimaryAddress()))
                .setSecondaryAddress(toDto(client.getSecondaryAddress()));
    }

    /**
     * Converts a dto to a client.
     *
     * @param clientDto the account dto
     * @return a client
     */
    public Client fromDto(final ClientDto clientDto) {
        return new Client()
                .setName(clientDto.getName())
                .setSurname(clientDto.getSurname())
                .setPrimaryAddress(fromDto(clientDto.getPrimaryAddress()))
                .setSecondaryAddress(fromDto(clientDto.getSecondaryAddress()));
    }

    /**
     * Converts a list of clients to a list of client dto.
     *
     * @param clients a list of clients
     * @return a list of client dto
     */
    public List<ClientDto> toDtoList(final List<Client> clients) {
        return clients.stream().map(this::toDto).collect(toList());
    }

    /**
     * Converts an address to a dto.
     *
     * @param address the address
     * @return an addresss dto
     */
    private AddressDto toDto(final Address address) {
        if (isNull(address)) return null;
        return new AddressDto()
                .setAddressLine1(address.getAddressLine1())
                .setAddressLine2(address.getAddressLine2())
                .setCity(address.getCity())
                .setCountry(address.getCountry());
    }

    /**
     * Converts a dto to an address.
     *
     * @param addressDto the adress dto
     * @return an addres
     */
    private Address fromDto(final AddressDto addressDto) {
        if (isNull(addressDto)) return null;
        return new Address()
                .setAddressLine1(addressDto.getAddressLine1())
                .setAddressLine2(addressDto.getAddressLine2())
                .setCity(addressDto.getCity())
                .setCountry(addressDto.getCountry());
    }
}
