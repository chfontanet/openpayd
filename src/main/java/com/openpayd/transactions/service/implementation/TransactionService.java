package com.openpayd.transactions.service.implementation;

import com.google.common.collect.ImmutableList;
import com.openpayd.transactions.exceptions.BadRequestException;
import com.openpayd.transactions.exceptions.NotFoundException;
import com.openpayd.transactions.persistence.model.Account;
import com.openpayd.transactions.persistence.model.Transaction;
import com.openpayd.transactions.persistence.repository.AccountRepository;
import com.openpayd.transactions.persistence.repository.TransactionRepository;
import com.openpayd.transactions.rest.dto.TransactionDto;
import com.openpayd.transactions.service.api.ITransactionService;
import com.openpayd.transactions.service.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.openpayd.transactions.persistence.model.BalanceStatus.CR;
import static com.openpayd.transactions.persistence.model.BalanceStatus.DR;
import static java.lang.Double.compare;
import static java.lang.String.format;

/**
 * The transaction service implementation
 * Transactional class, only commit all changes in database if the whole method finishes correctly.
 */
@Service
@Transactional
public class TransactionService implements ITransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(final TransactionRepository transactionRepository,
                              final AccountRepository accountRepository,
                              final TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;
    }


    /**
     * Search in database and returns all transactions belonging to an account, debited or credited.
     * Throws an exception if the account doesn't exist in database.
     *
     * @param accountId the account id
     * @return a list of transaction dto
     */
    @Override
    public List<TransactionDto> getAccountTransactions(final Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(format("Account with ID %s not found in Database", accountId)));
        final List<Transaction> transactions = transactionRepository.findByDebitOrDebitAccount(accountId).orElse(ImmutableList.of());
        return transactionMapper.toDtoList(transactions);
    }

    /**
     * Persist in database the received transaction, and updates both accounts, debited and credited,
     * modifying their balance and balance status.
     * Throws an exception if:
     * - The amount received is 0 or negative
     * - The debit account doesn't exist in database
     * - The credit account doesn't exist in database
     * Synchronized method to avoid concurrent calls while updating accounts
     *
     * @param transactionDto the transacion dto
     * @return a persisted transaction dto
     */
    @Override
    public synchronized TransactionDto putTransaction(final TransactionDto transactionDto) {
        final Transaction transaction = transactionMapper.fromDto(transactionDto);
        final Double amount = transaction.getAmount();
        if (amount <= 0) throw new BadRequestException("Invalid amount for transaction");
        final Account debitAccount = accountRepository.findById(transaction.getDebitAccount())
                .orElseThrow(() -> new NotFoundException(format("Debit Account with ID %s not found in Database",
                        transaction.getDebitAccount())));
        final Account creditAccount = accountRepository.findById(transaction.getCreditAccount())
                .orElseThrow(() -> new NotFoundException(format("Crebit Account with ID %s not found in Database",
                        transaction.getCreditAccount())));

        // Debit account
        debitAccount.setBalance(debitAccount.getBalance() - amount);
        setBalanceStatus(debitAccount);
        accountRepository.save(debitAccount);

        // Credit account
        creditAccount.setBalance(creditAccount.getBalance() + amount);
        setBalanceStatus(creditAccount);
        accountRepository.save(creditAccount);

        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    /**
     * Change the balance status of an account based on its balance.
     *
     * @param account the account
     */
    private void setBalanceStatus(final Account account) {
        if (compare(account.getBalance(), 0.0) < 0) {
            account.setBalanceStatus(DR);
        } else {
            account.setBalanceStatus(CR);
        }
    }
}
