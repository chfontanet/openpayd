package com.openpayd.transactions.service.implementation;

import com.google.common.collect.Lists;
import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.persistence.repository.AddressRepository;
import com.openpayd.transactions.persistence.repository.ClientRepository;
import com.openpayd.transactions.rest.dto.ClientDto;
import com.openpayd.transactions.service.api.IClientService;
import com.openpayd.transactions.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * The service client implementation
 */
@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ClientMapper clientMapper;

    public ClientService(final ClientRepository clientRepository,
                         final AddressRepository addressRepository,
                         final ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Search in database and returns all existing clients.
     *
     * @return a list of client dto
     */
    @Override
    public List<ClientDto> getClients() {
        return clientMapper.toDtoList(Lists.newArrayList(clientRepository.findAll()));
    }

    /**
     * Search in database and returns a client based on its id.
     *
     * @param id the client id
     * @return a client dto
     */
    @Override
    public ClientDto getClient(final Long id) {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("Client with ID %s not found in Database", id)));
        return clientMapper.toDto(client);
    }

    /**
     * Persists in database a new client.
     *
     * @param clientDto the client dto
     * @return a created client dto
     */
    @Override
    public ClientDto postClient(final ClientDto clientDto) {
        final Client client = clientMapper.fromDto(clientDto);
        addressRepository.save(client.getPrimaryAddress());
        if (Objects.nonNull(client.getSecondaryAddress())) {
            addressRepository.save(client.getSecondaryAddress());
        }
        return clientMapper.toDto(clientRepository.save(client));
    }
}
