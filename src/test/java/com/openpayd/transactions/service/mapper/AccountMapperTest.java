package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.exceptions.BadRequestException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.rest.dto.AccountDto;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.openpayd.transactions.service.ModelHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountMapperTest {

    private final AccountMapper accountMapper = new AccountMapper();

    @Test
    public void testToDtoOk() {
        final AccountDto accountDto = accountMapper.toDto(getAccount());
        assertEquals(getAccountDto(), accountDto);
    }

    @Test
    public void testToDtoListOk() {
        final Long id2 = 2L;
        final Long id3 = 3L;
        final List<AccountDto> dtoList = accountMapper.toDtoList(of(getAccount(), getAccount(id2), getAccount(id3)));
        assertEquals(3, dtoList.size());
        assertEquals(ID, dtoList.get(0).getId());
        assertEquals(id2, dtoList.get(1).getId());
        assertEquals(id3, dtoList.get(2).getId());
    }

    @Test
    public void testToDtoListEmptyOk() {
        final List<AccountDto> dtoList = accountMapper.toDtoList(of());
        assertTrue(dtoList.isEmpty());
    }

    @Test
    public void testFromDtoOk() {
        final Account account = accountMapper.fromDto(getAccountDto());
        assertEquals(getAccountTransformed(), account);
    }

    @Test(expected = BadRequestException.class)
    public void testFromDtoInvalidTypeThrowsException() {
        accountMapper.fromDto(getAccountDtoInvalidType());
    }

    @Test(expected = BadRequestException.class)
    public void testFromDtoInvalidBalanceThrowsException() {
        accountMapper.fromDto(getAccountDtoInvalidBalance());
    }
}