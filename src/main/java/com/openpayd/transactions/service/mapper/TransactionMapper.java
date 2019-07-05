package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.persistence.model.Transaction;
import com.openpayd.transactions.rest.dto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Mapper class to convert between model and DTO
 */
@Component
public class TransactionMapper {

    /**
     * Converts a transaction to a dto.
     *
     * @param transaction the transaction
     * @return a transaction dto
     */
    public TransactionDto toDto(final Transaction transaction) {
        return new TransactionDto()
                .setId(transaction.getId())
                .setDebitAccount(transaction.getDebitAccount())
                .setCreditAccount(transaction.getCreditAccount())
                .setAmount(transaction.getAmount())
                .setMessage(transaction.getMessage())
                .setDateCreated(transaction.getDateCreated());
    }

    /**
     * Converts a dto to a transaction.
     *
     * @param transactionDto the transaction dto
     * @return a transaction
     */
    public Transaction fromDto(final TransactionDto transactionDto) {
        return new Transaction()
                .setDebitAccount(transactionDto.getDebitAccount())
                .setCreditAccount(transactionDto.getCreditAccount())
                .setAmount(transactionDto.getAmount())
                .setMessage(transactionDto.getMessage());
    }

    /**
     * Converts a list of transactions to a list of transaction dto.
     *
     * @param transactions a list of transactions
     * @return a list of transaction dto
     */
    public List<TransactionDto> toDtoList(final List<Transaction> transactions) {
        return transactions.stream().map(this::toDto).collect(toList());
    }
}
