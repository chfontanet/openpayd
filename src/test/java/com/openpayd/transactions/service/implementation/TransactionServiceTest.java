package com.openpayd.transactions.service.implementation;

import com.google.common.collect.ImmutableList;
import com.openpayd.transactions.exceptions.BadRequestException;
import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.Transaction;
import com.openpayd.transactions.persistence.repository.AccountRepository;
import com.openpayd.transactions.persistence.repository.TransactionRepository;
import com.openpayd.transactions.rest.dto.TransactionDto;
import com.openpayd.transactions.service.mapper.TransactionMapper;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    private TransactionMapper transactionMapper = new TransactionMapper();

    private TransactionService transactionService;

    @Before
    public void setUp() {
        transactionService = new TransactionService(transactionRepository, accountRepository, transactionMapper);
    }

    @Test
    public void testGetAccountTransactionsEmptyOk() {
        when(accountRepository.findById(ID)).thenReturn(of(getAccount()));
        when(transactionRepository.findByDebitOrDebitAccount(ID)).thenReturn(empty());
        final List<TransactionDto> transactions = transactionService.getAccountTransactions(ID);
        assertTrue(transactions.isEmpty());
    }

    @Test
    public void testGetAccountTransactionsOk() {
        when(accountRepository.findById(ID)).thenReturn(of(getAccount()));
        when(transactionRepository.findByDebitOrDebitAccount(ID)).thenReturn(of(ImmutableList.of(getTransaction())));
        final List<TransactionDto> transactions = transactionService.getAccountTransactions(ID);
        assertEquals(1, transactions.size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetAccountTransactionsInvalidAccountThrowsException() {
        when(accountRepository.findById(ID)).thenReturn(empty());
        transactionService.getAccountTransactions(ID);
    }

    @Test
    public void testPutTransactionOk() {
        final TransactionDto transactionDto = getTransactionDtoWithAmount(5.0);
        when(accountRepository.findById(transactionDto.getDebitAccount())).thenReturn(of(getAccount(transactionDto.getDebitAccount())));
        when(accountRepository.findById(transactionDto.getCreditAccount())).thenReturn(of(getAccount(transactionDto.getCreditAccount())));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(getTransactionTransformed());

        final TransactionDto response = transactionService.putTransaction(transactionDto);

        verify(accountRepository, times(2)).save(any(Account.class));
        assertNotNull(transactionDto);
    }

    @Test(expected = BadRequestException.class)
    public void testPutTransactionInvalidAmountThrowsException() {
        transactionService.putTransaction(getTransactionDtoWithAmount(-1.0));
    }

    @Test(expected = NotFoundException.class)
    public void testPutTransactionInvalidDebitAccountThrowsException() {
        final TransactionDto transactionDto = getTransactionDto();
        when(accountRepository.findById(transactionDto.getDebitAccount())).thenReturn(empty());
        transactionService.putTransaction(transactionDto);
    }

    @Test(expected = NotFoundException.class)
    public void testPutTransactionInvalidCreditAccountThrowsException() {
        final TransactionDto transactionDto = getTransactionDto();
        when(accountRepository.findById(transactionDto.getDebitAccount())).thenReturn(of(getAccount(transactionDto.getDebitAccount())));
        when(accountRepository.findById(transactionDto.getCreditAccount())).thenReturn(empty());
        transactionService.putTransaction(transactionDto);

    }
}