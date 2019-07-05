package com.openpayd.transactions.persistence.repository;

import com.openpayd.transactions.persistence.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The transaction repository
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Returns all the transactions created or received belonging to the received account id.
     *
     * @param accountId the account id
     * @return an optional list of transactions for the account id received
     */
    @Query("SELECT t FROM Transaction t WHERE debitAccount = :accountId OR creditAccount = :accountId")
    Optional<List<Transaction>> findByDebitOrDebitAccount(@Param("accountId") final Long accountId);
}
