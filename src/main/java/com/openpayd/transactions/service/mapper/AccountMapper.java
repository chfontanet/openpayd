package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.exceptions.BadRequestException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.BalanceStatus;
import com.openpayd.transactions.persistence.model.Type;
import com.openpayd.transactions.rest.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * Mapper class to convert between model and DTO
 */
@Component
public class AccountMapper {

    /**
     * Converts an account to a dto.
     *
     * @param account the account
     * @return an account dto
     */
    public AccountDto toDto(final Account account) {
        return new AccountDto()
                .setId(account.getId())
                .setType(account.getType().name())
                .setBalance(account.getBalance())
                .setBalanceStatus(account.getBalanceStatus().name())
                .setDateCreated(account.getDateCreated());
    }

    /**
     * Converts a dto to an account.
     *
     * @param accountDto the account dto
     * @return an account
     */
    public Account fromDto(final AccountDto accountDto) {
        return new Account()
                .setType(getTypeFromEnum(accountDto.getType()))
                .setBalance(accountDto.getBalance())
                .setBalanceStatus(getBalanceStatusFromEnum(accountDto.getBalanceStatus()));
    }

    /**
     * Converts a list of accounts to a list of account dto.
     *
     * @param accounts a list of accounts
     * @return a list of account dto
     */
    public List<AccountDto> toDtoList(final List<Account> accounts) {
        return accounts.stream().map(this::toDto).collect(toList());
    }

    /**
     * Converts a received string to a type or throws an appropriate exception.
     *
     * @param type the type received in string format
     * @return the related type
     */
    private Type getTypeFromEnum(final String type) {
        try {
            return Type.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(format("Type %s not valid", type));
        }
    }

    /**
     * Converts a received string to a balance status or throws an appropriate exception.
     *
     * @param balanceStatus the balance status received in string format
     * @return the related balance status
     */
    private BalanceStatus getBalanceStatusFromEnum(final String balanceStatus) {
        try {
            return BalanceStatus.valueOf(balanceStatus);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(format("BalanceStatus %s not valid", balanceStatus));
        }
    }
}
