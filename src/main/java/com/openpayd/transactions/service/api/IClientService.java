package com.openpayd.transactions.service.api;

import com.openpayd.transactions.rest.dto.ClientDto;

import java.util.List;

/**
 * The client service interface
 */
public interface IClientService {

    List<ClientDto> getClients();
    ClientDto getClient(final Long id);
    ClientDto postClient(final ClientDto clientDto);
}
