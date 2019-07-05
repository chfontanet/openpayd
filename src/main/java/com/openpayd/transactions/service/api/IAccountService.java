package com.openpayd.transactions.service.api;

import com.openpayd.transactions.rest.dto.AccountDto;

import java.util.List;

/**
 * The account service interface
 */
public interface IAccountService {

    List<AccountDto> getClientAccounts(final Long clientId);
    AccountDto postClientAccount(final Long clientId, final AccountDto accountDto);
}
