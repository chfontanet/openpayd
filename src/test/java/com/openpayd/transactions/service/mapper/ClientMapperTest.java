package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.rest.dto.ClientDto;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.openpayd.transactions.service.ModelHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientMapperTest {

    private final ClientMapper clientMapper = new ClientMapper();

    @Test
    public void testToDtoTwoAddressesOk() {
        final ClientDto clientDto = clientMapper.toDto(getClient(true));
        assertEquals(getClientDto(true), clientDto);
    }

    @Test
    public void testToDtoOneAddressOk() {
        final ClientDto clientDto = clientMapper.toDto(getClient(false));
        assertEquals(getClientDto(false), clientDto);
    }

    @Test
    public void testToDtoListOk() {
        final Long id2 = 2L;
        final Long id3 = 3L;
        final List<ClientDto> dtoList = clientMapper.toDtoList(of(getClient(ID), getClient(id2), getClient(id3)));
        assertEquals(3, dtoList.size());
        assertEquals(ID, dtoList.get(0).getId());
        assertEquals(id2, dtoList.get(1).getId());
        assertEquals(id3, dtoList.get(2).getId());
    }

    @Test
    public void testToDtoListEmptyOk() {
        final List<ClientDto> dtoList = clientMapper.toDtoList(of());
        assertTrue(dtoList.isEmpty());
    }

    @Test
    public void testFromDtoTwoAddressesOk() {
        final Client client = clientMapper.fromDto(getClientDto(true));
        assertEquals(getClientTransformed(true), client);
    }

    @Test
    public void testFromDtoOneAddressOk() {
        final Client client = clientMapper.fromDto(getClientDto(false));
        assertEquals(getClientTransformed(false), client);
    }
}