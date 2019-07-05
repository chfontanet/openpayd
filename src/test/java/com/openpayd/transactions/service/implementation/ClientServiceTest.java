package com.openpayd.transactions.service.implementation;

import com.google.common.collect.ImmutableList;
import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Address;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.persistence.repository.AddressRepository;
import com.openpayd.transactions.persistence.repository.ClientRepository;
import com.openpayd.transactions.rest.dto.ClientDto;
import com.openpayd.transactions.service.mapper.ClientMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.openpayd.transactions.service.ModelHelper.*;
import static java.util.Optional.empty;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AddressRepository addressRepository;

    private ClientMapper clientMapper = new ClientMapper();

    private ClientService clientService;

    @Before
    public void setUp() {
        clientService = new ClientService(clientRepository, addressRepository, clientMapper);
    }

    @Test
    public void testGetClientsEmptyOk() {
        when(clientRepository.findAll()).thenReturn(ImmutableList.of());
        final List<ClientDto> clientDtos = clientService.getClients();
        assertTrue(clientDtos.isEmpty());
    }

    @Test
    public void testGetClientsOk() {
        when(clientRepository.findAll()).thenReturn(ImmutableList.of(getClient(ID)));
        final List<ClientDto> clientDtos = clientService.getClients();
        assertEquals(1, clientDtos.size());
    }

    @Test
    public void testGetClientOk() {
        when(clientRepository.findById(ID)).thenReturn(Optional.of(getClient(ID)));
        final ClientDto clientDto = clientService.getClient(ID);
        assertNotNull(clientDto);
    }

    @Test(expected = NotFoundException.class)
    public void testGetInvalidClientThrowsException() {
        when(clientRepository.findById(ID)).thenReturn(empty());
        clientService.getClient(ID);
    }

    @Test
    public void testPostClient() {
        when(addressRepository.save(any(Address.class))).thenReturn(getAddress("1"));
        when(clientRepository.save(any(Client.class))).thenReturn(getClient(false));
        final ClientDto clientDto = clientService.postClient(getClientDto(false));
        assertNotNull(clientDto);
    }
}