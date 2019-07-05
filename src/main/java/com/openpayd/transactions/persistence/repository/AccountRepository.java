package com.openpayd.transactions.persistence.repository;

import com.openpayd.transactions.persistence.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * The account repository
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    /**
     * Returns all the accounts belonging to the received client id.
     *
     * @param clientId the client id
     * @return an optional list of accounts for the client id received
     */
    Optional<List<Account>> findByClientId(final Long clientId);
}
