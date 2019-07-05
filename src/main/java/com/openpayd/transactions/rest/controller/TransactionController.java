package com.openpayd.transactions.rest.controller;

import com.openpayd.transactions.rest.dto.TransactionDto;
import com.openpayd.transactions.service.api.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * The transaction controller.
 * Contain the endpoints to:
 * - GET all transactions belonging to an account
 * - PUT a new transaction
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private ITransactionService transactionService;

    @Autowired
    public TransactionController(final ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Returns all transactions belonging to an account.
     *
     * @param accountId the account id
     * @return a ResponseEntity with a list of transactions
     */
    @GetMapping(value = "/account/{id}/transactions")
    public ResponseEntity<List<TransactionDto>> getAccountTransactions(@PathVariable(value = "id") final Long accountId) {
        return ok(transactionService.getAccountTransactions(accountId));
    }

    /**
     * Creates a transaction and updates both accounts, debited and credited with balance and type.
     *
     * @param transactionDto the tracsaction dto object received
     * @return
     */
    @PutMapping(value = "/transaction")
    public ResponseEntity<TransactionDto> putTransaction(@Validated @RequestBody final TransactionDto transactionDto) {
        return status(OK).body(transactionService.putTransaction(transactionDto));
    }
}
