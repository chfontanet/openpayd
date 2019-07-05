package com.openpayd.transactions.service.api;

import com.openpayd.transactions.rest.dto.TransactionDto;

import java.util.List;

/**
 * The transaction service interface
 */
public interface ITransactionService {

    List<TransactionDto> getAccountTransactions(final Long accountId);
    TransactionDto putTransaction(final TransactionDto transactionDto);
}
