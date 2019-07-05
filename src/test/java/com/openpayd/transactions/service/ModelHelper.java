package com.openpayd.transactions.service;

import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.Address;
import com.openpayd.transactions.persistence.model.Client;
import com.openpayd.transactions.persistence.model.Transaction;
import com.openpayd.transactions.rest.dto.AccountDto;
import com.openpayd.transactions.rest.dto.AddressDto;
import com.openpayd.transactions.rest.dto.ClientDto;
import com.openpayd.transactions.rest.dto.TransactionDto;

import java.util.Date;

import static com.openpayd.transactions.persistence.model.BalanceStatus.DR;
import static com.openpayd.transactions.persistence.model.Type.CURRENT;
import static com.openpayd.transactions.persistence.model.Type.SAVINGS;

public class ModelHelper {

    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String ADDRESS_LINE_1 = "address_line_1_";
    public static final String ADDRESS_LINE_2 = "address_line_2_";
    public static final String CITY = "city_";
    public static final String COUNTRY = "country_";
    public static final Double BALANCE = 10.0;
    public static final Date DATE_CREATED = new Date();
    public static final Long DEBIT_ACCOUNT = 22L;
    public static final Long CREDIT_ACCOUNT = 33L;
    public static final Double AMOUNT = 10.0;
    public static final String MESSAGE = "transfer from A to B";



    public static ClientDto getClientDto(final boolean secondaryAddress) {
        return new ClientDto()
                .setId(ID)
                .setName(NAME)
                .setSurname(SURNAME)
                .setPrimaryAddress(getAddressDto("1"))
                .setSecondaryAddress(secondaryAddress ? getAddressDto("2") : null);
    }

    public static AddressDto getAddressDto(final String numAddress) {
        return new AddressDto()
                .setAddressLine1(ADDRESS_LINE_1 + numAddress)
                .setAddressLine2(ADDRESS_LINE_2 + numAddress)
                .setCity(CITY + numAddress)
                .setCountry(COUNTRY + numAddress);
    }

    public static Client getClient(final Long id) {
        return getClientTransformed(false)
                .setId(id);
    }

    public static Client getClient(final boolean secondaryAddress) {
        return getClientTransformed(secondaryAddress)
                .setId(ID);
    }

    public static Client getClientTransformed(final boolean secondaryAddress) {
        return new Client()
                .setName(NAME)
                .setSurname(SURNAME)
                .setPrimaryAddress(getAddress("1"))
                .setSecondaryAddress(secondaryAddress ? getAddress("2") : null);
    }

    public static Address getAddress(final String numAddress) {
        return new Address()
                .setAddressLine1(ADDRESS_LINE_1 + numAddress)
                .setAddressLine2(ADDRESS_LINE_2 + numAddress)
                .setCity(CITY + numAddress)
                .setCountry(COUNTRY +numAddress);
    }

    public static AccountDto getAccountDto() {
        return new AccountDto()
                .setId(ID)
                .setType(CURRENT.name())
                .setBalance(BALANCE)
                .setBalanceStatus(DR.name())
                .setDateCreated(DATE_CREATED);
    }

    public static AccountDto getAccountDtoInvalidType() {
        return new AccountDto()
                .setId(ID)
                .setType("NORMAL")
                .setBalance(BALANCE)
                .setBalanceStatus(DR.name())
                .setDateCreated(DATE_CREATED);
    }

    public static AccountDto getAccountDtoInvalidBalance() {
        return new AccountDto()
                .setId(ID)
                .setType(SAVINGS.name())
                .setBalance(BALANCE)
                .setBalanceStatus("CREDIT")
                .setDateCreated(DATE_CREATED);
    }

    public static Account getAccount(final Long id) {
        return getAccountTransformed()
                .setId(id)
                .setDateCreated(DATE_CREATED);
    }

    public static Account getAccount() {
        return getAccountTransformed()
                .setId(ID)
                .setDateCreated(DATE_CREATED);
    }

    public static Account getAccountTransformed() {
        return new Account()
                .setType(CURRENT)
                .setBalance(BALANCE)
                .setBalanceStatus(DR);
    }

    public static TransactionDto getTransactionDto() {
        return getTransactionDtoWithAmount(AMOUNT);
    }

    public static TransactionDto getTransactionDtoWithAmount(final Double amount) {
        return new TransactionDto()
                .setId(ID)
                .setDebitAccount(DEBIT_ACCOUNT)
                .setCreditAccount(CREDIT_ACCOUNT)
                .setAmount(amount)
                .setMessage(MESSAGE)
                .setDateCreated(DATE_CREATED);
    }

    public static Transaction getTransactionTransformed() {
        return new Transaction()
                .setDebitAccount(DEBIT_ACCOUNT)
                .setCreditAccount(CREDIT_ACCOUNT)
                .setAmount(AMOUNT)
                .setMessage(MESSAGE);
    }

    public static Transaction getTransaction(final Long id) {
        return getTransactionTransformed()
                .setId(id)
                .setDateCreated(DATE_CREATED);
    }

    public static Transaction getTransaction() {
        return getTransactionTransformed()
                .setId(ID)
                .setDateCreated(DATE_CREATED);
    }

    public static Account getAccountFromDB() {
        return new Account()
                .setClient(new Client().setId(ID))
                .setType(CURRENT)
                .setBalance(10.0)
                .setBalanceStatus(DR);
    }
}
