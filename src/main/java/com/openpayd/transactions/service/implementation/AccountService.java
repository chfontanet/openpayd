package com.openpayd.transactions.service.implementation;

import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.persistence.repository.AccountRepository;
import com.openpayd.transactions.persistence.repository.ClientRepository;
import com.openpayd.transactions.rest.dto.AccountDto;
import com.openpayd.transactions.service.api.IAccountService;
import com.openpayd.transactions.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.format;

/**
 * The service account implementation
 */
@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(final AccountRepository accountRepository,
                          final ClientRepository clientRepository,
                          final AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.accountMapper = accountMapper;
    }


    /**
     * Search in database and returns all accounts belonging to a client by its id.
     *
     * @param clientId the client id
     * @return a list of account dto
     */
    @Override
    public List<AccountDto> getClientAccounts(final Long clientId) {
        final List<Account> accountList = accountRepository.findByClientId(clientId).orElse(of());
        return accountMapper.toDtoList(accountList);
    }

    /**
     * Persists in database a new account belonging to a received client or throw an exception if client doesn't exist.
     *
     * @param clientId   the client id
     * @param accountDto the account dto
     * @return the created account dto
     */
    @Override
    public AccountDto postClientAccount(final Long clientId, final AccountDto accountDto) {
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException(format("Client with ID %s not found in Database", clientId)));
        final Account account = accountMapper.fromDto(accountDto).setClient(client);
        return accountMapper.toDto(accountRepository.save(account));
    }
}
