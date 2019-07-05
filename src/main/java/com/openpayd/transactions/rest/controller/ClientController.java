package com.openpayd.transactions.rest.controller;

import com.openpayd.transactions.rest.dto.AccountDto;
import com.openpayd.transactions.rest.dto.ClientDto;
import com.openpayd.transactions.service.api.IAccountService;
import com.openpayd.transactions.service.api.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * The client controller.
 * Contain the endpoints to:
 * - GET all clients
 * - GET a client by its id
 * - POST a new client
 * - GET accounts belonging to a client by its id
 * - POST a new account to a client by its id
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class ClientController {

    private final IAccountService accountService;
    private final IClientService clientService;

    @Autowired
    public ClientController(final IAccountService accountService,
                            final IClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    /**
     * Returns all clients in database.
     *
     * @return a ResponseEntity with a list of clients
     */
    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientDto>> getClients() {
        return ok(clientService.getClients());
    }

    /**
     * Returns a client by its id.
     *
     * @param clientId the client id
     * @return a ResponseEntity with a client
     */
    @GetMapping(value = "/client/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable(value = "id") final Long clientId) {
        return ok(clientService.getClient(clientId));
    }

    /**
     * Creates a new client in database.
     *
     * @param clientDto the client dto object received
     * @return a ResponseEntity with the created client
     */
    @PostMapping(value = "/client", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> postClient(@Validated @RequestBody final ClientDto clientDto) {
        return status(CREATED).body(clientService.postClient(clientDto));
    }

    /**
     * Returns all accounts belonging to a client by its id.
     *
     * @param clientId the client id
     * @return a ResponseEntity with a list of accounts
     */
    @GetMapping(value = "/client/{id}/accounts")
    public ResponseEntity<List<AccountDto>> getClientAccounts(@PathVariable(value = "id") final Long clientId) {
        return ok(accountService.getClientAccounts(clientId));
    }

    /**
     * Creates a new account for the received client in database.
     *
     * @param clientId   the client id
     * @param accountDto the account dto object received
     * @return a ResponseEntity with the created account
     */
    @PostMapping(value = "/client/{id}/account", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> postClientAccount(@PathVariable(value = "id") final Long clientId,
                                                        @Validated @RequestBody final AccountDto accountDto) {
        return status(CREATED).body(accountService.postClientAccount(clientId, accountDto));
    }
}
