package com.openpayd.transactions.service.implementation;

import com.google.common.collect.ImmutableList;
import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.persistence.repository.AccountRepository;
import com.openpayd.transactions.persistence.repository.ClientRepository;
import com.openpayd.transactions.rest.dto.AccountDto;
import com.openpayd.transactions.service.api.IAccountService;
import com.openpayd.transactions.service.mapper.AccountMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.openpayd.transactions.service.ModelHelper.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    private AccountMapper accountMapper = new AccountMapper();

    private IAccountService accountService;

    @Before
    public void setUp() {
        accountService = new AccountService(accountRepository, clientRepository, accountMapper);
    }

    private static final Long ID = 1L;

    @Test
    public void testGetClientAccountsInvalidClientEmptyListOk() {
        when(accountRepository.findByClientId(ID)).thenReturn(empty());
        final List<AccountDto> clientAccounts = accountService.getClientAccounts(ID);
        assertTrue(clientAccounts.isEmpty());
    }

    @Test
    public void testGetClientAccountsOk() {
        when(accountRepository.findByClientId(ID)).thenReturn(of(ImmutableList.of(getAccount())));
        final List<AccountDto> clientAccounts = accountService.getClientAccounts(ID);
        assertEquals(1, clientAccounts.size());
    }

    @Test
    public void testPostClientAccountOk() {
        final Account account = getAccountFromDB();
        when(clientRepository.findById(ID)).thenReturn(of(new Client().setId(ID)));
        when(accountRepository.save(account)).thenReturn(account);
        final AccountDto accountDto = accountService.postClientAccount(ID, getAccountDto());
        assertNotNull(accountDto);
    }

    @Test(expected = NotFoundException.class)
    public void testPostClientAccountInvalidClientThrowsException() {
        when(clientRepository.findById(ID)).thenReturn(empty());
        accountService.postClientAccount(ID, new AccountDto());
    }
}